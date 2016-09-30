import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {
		long m = System.currentTimeMillis();
		
		Sentence s1 = new Sentence(2,false);//A
		Sentence s2 = new Sentence(3,false);//B
		Sentence s3 = new Sentence(4,false);//C
		Sentence s4 = new Sentence(s1,s2,2,false);//A>B
		Sentence s5 = new Sentence(s2,s3,2,false);//B>C
		Sentence s6 = new Sentence(s1,s5,1,false);//A^(B>C)
		Sentence s7 = new Sentence(s6,s4,1,false);//(A^(B>C))^(A>C)
		Sentence s8 = s1.negate().negate();//~~A
		Sentence s9 = new Sentence(s1,s5,2,false);//A>(B>C)
		Sentence s10 = new Sentence(s9,s8,1,false);//(A>(B>C))^~~A
		Sentence s11 = new Sentence(s10,s2,1,false);//(((A>(B>C))^~~A)^B)
		Sentence s12 = new Sentence(s1,s2,0,false);//(AvB)
		Sentence s13 = new Sentence(s1,s3,0,false);//(AvC)
		Sentence s14 = s1.negate();//~A
		Sentence s15 = new Sentence(s2,s3,1,false);//(B^C)
		Sentence s16 = new Sentence(s12,s13,1,false);//(AvB)^(AvC)
		Sentence s17 = new Sentence(s16,s14,1,false);//(((AvB)^(AvC))^~A)
		Sentence s18 = new Sentence(s2,s1,2,false);//B>A
		Sentence s19 = new Sentence(s4,s18,2,false);//(A>B)>(B>A)
		Sentence s20 = new Sentence(s4,s19,1,false);//((A>B)^(A>B)>(B>A))
		Sentence s21 = new Sentence(s1,s2,3,false);//A<>B
		Sentence s22 = new Sentence(5,false);//D
		Sentence s23 = new Sentence(6,false);//S
		Sentence s24 = new Sentence(s23,s22,1,false);//(S^D)
		Sentence s25 = new Sentence(s1,s24,3,false);//A<>(S^D)
		Sentence s26 = new Sentence(s24,s1,2,false);//(S^D)>A
		Sentence s27 = new Sentence(s25,s24,1,false);//((A<>(S^D))^(S^D)
		Sentence s28 = new Sentence(s27,s26,1,false);//(((A<>(S^D))^(S^D))^((S^D)>A))
		Sentence s29 = s2.negate();
		Sentence s30 = new Sentence(s29,s3,1,false);//~B^C
		Sentence s31 = new Sentence(s4,s30,1,false);//(A>B)^(~B^C)
		Sentence s32 = new Sentence(s31,s29,1,false);//((A>B)^(~B^C))^~B
		Sentence s33 = new Sentence(s3,s8,1,false);//C^~A
		Sentence s34 = new Sentence(7,false);//R
		Sentence s35 = new Sentence(8,false);//X
		Sentence s36 = s22.negate();//~D
		Sentence s37 = new Sentence(s36,s34,2,false);//(~D>R)
		Sentence s38 = new Sentence(s22,s34,0,false);//(DvR)
		Sentence s39 = new Sentence(s37,s38,1,false);//((~D>R)^(DvR))
		Sentence s40 = new Sentence(s39,s23,1,false);//(((~D>R)^(DvR))^S)
		Sentence s41 = new Sentence(s1,s22,0,false);//(Av~D)
		Sentence s42 = new Sentence(s40,s41,1,false);//((((~D>R)^(DvR))^S)^(Av~D))
		Sentence s43 = new Sentence(s23,s22,2,false);//(S>D)
		Sentence s44 = new Sentence(s42,s43,1,false);//(((((~D>R)^(DvR))^S)^(Av~D))^(S>D))
		Sentence s45 = new Sentence(s1,s35,2,false);//(A>X)
		Sentence s46 = new Sentence(s44,s45,1,false);//((((((~D>R)^(DvR))^S)^(Av~D))^(S>D))^(A>X))
		Sentence s47 = new Sentence(9,false);//P
		Sentence s48 = new Sentence(10,false);//T
		Sentence s49 = s47.negate();//~P
		Sentence s50 = s48.negate();//~T
		Sentence s51 = new Sentence(s49,s50,0,false);//(~Pv~T)
		Sentence s52 = new Sentence(s48,s35,2,false);//(T>X)
		Sentence s53 = new Sentence(s51,s52,1,false);//((~Pv~T)^(T>X))
		Sentence s54 = new Sentence(s53,s50,1,false);//(((~Pv~T)^(T>X))^~T)
		Sentence s55 = new Sentence(s35,s48,2,false);//(X>T)
		Sentence s56 = new Sentence(s54,s55,1,false);//((((~Pv~T)^(T>X))^~T)^(X>T))
		Sentence s57 = new Sentence(s35,s48,0,false);//(XvT)
		Sentence s58 = new Sentence(s56,s57,1,false);//(((((~Pv~T)^(T>X))^~T)^(X>T))^(XvT))
		Sentence s59 = new Sentence(s2,s1,0,false);//BvA
		Sentence s60 = new Sentence(s59,s14,1,false);//(BvA)^~A
		Sentence s61 = new Sentence(s4,s1,1,false);//((A>B)^A)
		//TESTE M:-------------------------------------------------------------------------
		Sentence tm = new Sentence (s61,s2,2,false);//((A>B)^A)>B
		SentSequence ssm = new SentSequence(tm.negate().negate(),null,0);
		//TESTE 0:-------------------------------------------------------------------------
		Sentence t0 = new Sentence(s60,s2,2,false);//((BvA)^~A)>B
		SentSequence ss0 = new SentSequence(t0.negate().negate(),null,0);
		//TESTE 1:-------------------------------------------------------------------------
		Sentence t1 = new Sentence(s7,s3,2,false);//((A^(B>C))^(A>C))>C
		SentSequence ss1 = new SentSequence(t1.negate().negate(),null,0);
		//TESTE 2:-------------------------------------------------------------------------
		Sentence t2 = new Sentence(s11,s3,2,false);//((((A>(B>C))^~~A)^B)>C)
		SentSequence ss2 = new SentSequence(t2.negate().negate(),null,0);
		//TESTE 3:-------------------------------------------------------------------------
		Sentence t3 = new Sentence(s17,s15,2,false);//((((2v3)^(2v4))^~2)>(3^4))
		SentSequence ss3 = new SentSequence(t3.negate().negate(),null,0);
		//TESTE 4:-------------------------------------------------------------------------
		Sentence t4 = new Sentence(s20,s21,2,false);//(((A>B)^((A>B)>(B>A)))>(A<>B))
		SentSequence ss4 = new SentSequence(t4.negate().negate(),null,0);
		//TESTE 5:-------------------------------------------------------------------------
		Sentence t5 = new Sentence(s28,s1,2,false);//((((A<>(S^D))^(S^D))^((S^D)>A))>A)
		SentSequence ss5 = new SentSequence(t5.negate().negate(),null,0);
		//TESTE 6:-------------------------------------------------------------------------
		Sentence t6 = new Sentence(s32,s33,2,false);//(((A>B)^(~B^C))^~B)>(C^~A)
		SentSequence ss6 = new SentSequence(t6.negate().negate(),null,0);
		//TESTE 7:-------------------------------------------------------------------------
		Sentence t7 = new Sentence(s46,s35,2,false);//(((((((~D>R)^(DvR))^S)^(Av~D))^(S>D))^(A>X))>X)
		SentSequence ss7 = new SentSequence(t7.negate().negate(),null,0);
		//TESTE 8:-------------------------------------------------------------------------
		Sentence t8 = new Sentence(s58,s35,2,false);
		SentSequence ss8 = new SentSequence(t8.negate().negate(),null,0);
		//---------------------------------------------------------------------------------
			
		Rules.addToArray(ssm);
		System.out.println("TESTE M.P.:");
		try {
			Rules.itera(ssm);
			if(!Rules.consistent){
				System.out.println("Inconsistente");
			}
			Rules.clearArray();	
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		Rules.addToArray(ss0);
		System.out.println("TESTE 0:");
		try {
			Rules.itera(ss0);
			if(!Rules.consistent){
				System.out.println("Inconsistente");
			}
			Rules.clearArray();			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		Rules.addToArray(ss1);
		System.out.println("TESTE 1:");
		try {
			Rules.itera(ss1);
			if(!Rules.consistent){
				System.out.println("Inconsistente");
			}
			Rules.clearArray();			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		Rules.addToArray(ss2);
		System.out.println("TESTE 2:");
		try {
			Rules.itera(ss2);
			if(!Rules.consistent){
				System.out.println("Inconsistente");
			}
			Rules.clearArray();			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		Rules.addToArray(ss3);
		System.out.println("TESTE 3:");
		try {
			Rules.itera(ss3);
			if(!Rules.consistent){
				System.out.println("Inconsistente");
			}
			Rules.clearArray();			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		Rules.addToArray(ss4);
		System.out.println("TESTE 4:");
		try {
			Rules.itera(ss4);
			if(!Rules.consistent){
				System.out.println("Inconsistente");
			}
			Rules.clearArray();			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		Rules.addToArray(ss5);
		System.out.println("TESTE 5:");
		try {
			Rules.itera(ss5);
			if(!Rules.consistent){
				System.out.println("Inconsistente");
			}
			Rules.clearArray();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		Rules.addToArray(ss6);
		System.out.println("TESTE 6:");
		try {
			Rules.itera(ss6);
			if(!Rules.consistent){
				System.out.println("Inconsistente");
			}
			Rules.clearArray();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		Rules.addToArray(ss7);
		System.out.println("TESTE 7:");
		try {
			Rules.itera(ss7);
			if(!Rules.consistent){
				System.out.println("Inconsistente");
			}
			Rules.clearArray();			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		Rules.addToArray(ss8);
		System.out.println("TESTE 8:");
		try {
			Rules.itera(ss8);
			if(!Rules.consistent){
				System.out.println("Inconsistente");
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Tempo de execução: "+(System.currentTimeMillis()-m)+" ms");
	}

}
