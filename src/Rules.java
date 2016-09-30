import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rules {
	static boolean arrayCleared=false;
	static boolean consistent=false;
	Sentence newSL;
	static ArrayList<SentSequence> everySent = new ArrayList<SentSequence>();
	static ArrayList<SentSequence> pivoSent = new ArrayList<SentSequence>();
	
	public static void clearArray(){
		everySent = new ArrayList<SentSequence>();
		pivoSent = new ArrayList<SentSequence>();
		consistent=false;
	}
	
	
	
	protected static boolean addToArray(SentSequence s){
		//sempre que adicionar um verdadeiro ou falso printa a sequencia ate ele
		if(checkList(s)){
			if(s.getThis().toString().equals("T")){
				consistent = true;
				System.out.println("verdadeiro!");
				System.out.println("sentenca: ");
				s.printSent();
			}
			if(s.getThis().toString().equals("F")){
				consistent = true;
				System.out.println("falso!");
				System.out.println("sentenca: ");
				s.printSent();
			}
			if(checkPivo(s)){
				everySent.add(s);
				if(arrayCleared){
					pivoSent.add(s);
				}
				return true;
			}
		}
		return false;
	}
	
	private static boolean checkList(SentSequence s) {
		for(int j=0;j<everySent.size();j++){
			if(everySent.get(j).getThis().toString().equals(s.getThis().toString())){
				return false;
			}
		}
		return true;
	}
	private static boolean checkPivo(SentSequence s) {
		for(int j=0;j<pivoSent.size();j++){
			if(pivoSent.get(j).getThis().toString().equals(s.getThis().toString())){
				return false;
			}
		}
		return true;
	}

	protected static void itera(SentSequence ss) throws CloneNotSupportedException {
		boolean added = true;
		arrayCleared=false;
		if(!ss.isExpanded()&&!ss.getThis().isAto()){
			ss.expand();
			Sentence ns = (Sentence) ss.getThis().clone();		
			
			while(added){
				added=false;
				Sentence newS = Rules.rule67ab(ns);
				added = tryToAdd(ss, added, ns, newS,6);
				if(arrayCleared){
					break;
				}
				newS = Rules.rule8ab(ns);
				added = tryToAdd(ss, added, ns, newS,8);
				if(arrayCleared){
					break;
				}
				newS = Rules.rule3ab(ns);
				added = tryToAdd(ss, added, ns, newS,3);
				if(arrayCleared){
					break;
				}
				newS = Rules.rule4ab(ns);
				added = tryToAdd(ss, added, ns, newS,4);
				if(arrayCleared){
					break;
				}
				newS = Rules.rule5ab(ns);
				added = tryToAdd(ss, added, ns, newS,5);
				if(arrayCleared){
					break;
				}
			}	
	
			for(int j=0;j<everySent.size();j++){
				if(!everySent.get(j).isExpandedIn()){
					iteraIn(everySent.get(j));
				}
			}
		}
	}
	
	protected static void iteraIn(SentSequence ss) throws CloneNotSupportedException {
		int innerCount=0;
		arrayCleared=false;
		if(!ss.getThis().isAto()&&!ss.isExpandedIn()){
			ss.expandIn();
			Sentence nsL = ss.getThis().getSL();
			while(!nsL.isAto()){
				boolean added = true;
				innerCount++;
				
				if(!nsL.checkSent(ss.getThis())){	
					while(added){
						added=false;
						
						Sentence newSL6 = Rules.rule67ab(nsL);
						Sentence ns6 = (Sentence) ss.getThis().clone();
						ns6.setInnerSL(newSL6,innerCount);
						added = tryToAdd(ss, added, ss.getThis(), ns6,6);
						if(arrayCleared){
							break;
						}
						
						Sentence newSL8 = Rules.rule8ab(nsL);
						Sentence ns8 = ss.getThis().clone();
						ns8.setInnerSL(newSL8,innerCount);	
						added = tryToAdd(ss, added, ss.getThis(), ns8,8);
						if(arrayCleared){
							break;
						}
						
						Sentence newSL3 = Rules.rule3ab(nsL);
						Sentence ns3 = (Sentence) ss.getThis().clone();
						ns3.setInnerSL(newSL3,innerCount);			
						added = tryToAdd(ss, added, ss.getThis(), ns3,3);
						if(arrayCleared){
							break;
						}
						
						Sentence newSL4 = Rules.rule4ab(nsL);
						Sentence ns4 = (Sentence) ss.getThis().clone();
						ns4.setInnerSL(newSL4,innerCount);					
						added = tryToAdd(ss, added, ss.getThis(), ns4,4);
						if(arrayCleared){
							break;
						}
						
						Sentence newSL5 = Rules.rule5ab(nsL);
						Sentence ns5 = (Sentence) ss.getThis().clone();
						ns5.setInnerSL(newSL5,innerCount);		
						added = tryToAdd(ss, added, ss.getThis(), ns5,5);
						if(arrayCleared){
							break;
						}
					}
				}
				if(arrayCleared){
					break;
				}
				nsL = nsL.getSL();
			}
		}
		
		for(int j=0;j<everySent.size();j++){		
			if(!everySent.get(j).isExpanded()){	
				itera(everySent.get(j));
			}
		}
	}

	private static boolean tryToAdd(SentSequence ss, boolean added, Sentence ns, Sentence newSL,int i) {
		SentSequence newSS;
		if(!newSL.checkSent(ns)){
			//checa se apos a aplicacao da regra ouve mudan�a na senten�a
			//printa a regra aplicada
			newSS = new SentSequence(newSL,ss,i);	
			if(!added){
				//se o valor for false, pode ser alterado, se for true nao pode
				added=addToArray(newSS);
			}
			addToArray(newSS);
		}
		return added;
	}
	
	public static Sentence rule1(Sentence s) throws CloneNotSupportedException{
		Sentence cs = (Sentence) s.clone();
		if(!s.isAto()){
			if(s.getOpr() == 3){
				cs.setOpr(1);
				Sentence newRight = new Sentence(cs.getSR(),cs.getSL(),2,false);
				Sentence newLeft = new Sentence(cs.getSL(),cs.getSR(),2,false);
				cs.setSR(newRight);
				cs.setSL(newLeft);
				return cs;
			}
		}
		return s;
	}
	
	public static Sentence rule2(Sentence s) throws CloneNotSupportedException{
		Sentence cs = (Sentence) s.clone();
		if(s.getOpr() == 2 && !s.isAto()){
			cs.setOpr(0);
			cs.setSL(cs.getSL().negate());
			return cs;
		}
		return s;
	}
	
	public static Sentence rule3ab(Sentence s) throws CloneNotSupportedException{
		Sentence cs = (Sentence) s.clone();
		if(!s.isAto() && (s.getOpr()==0 || s.getOpr()==1)){
			if(s.getSR().isAto()){
				if(s.getSL().isAto()){
					return s;
				}
			}
			return cs.swap();
		}
		return s;
	}
	
	public static Sentence rule4ab(Sentence s) throws CloneNotSupportedException{
		Sentence cs = (Sentence) s.clone();
		if(!s.isAto()){
			Sentence inner = (Sentence) cs.getSL();
			if(inner.getOpr()==cs.getOpr() && (inner.getOpr()==0 || inner.getOpr()==1) && (!inner.isAto())){
				Sentence newS4 = new Sentence(inner.getSR(),cs.getSR(),cs.getOpr(),false);
				Sentence newS5 = new Sentence(inner.getSL(),newS4,cs.getOpr(),false);/*
				if(newS5.checkAtoValues()){
					everySent = new ArrayList<SentSequence>();
					arrayCleared=true;
				}*/
				return newS5;
			}
		}
		return s;
	}
	
	public static Sentence rule5ab(Sentence s) throws CloneNotSupportedException{
		Sentence cs = (Sentence) s.clone();
		Sentence inner = cs.getSR();
		if(inner==null||cs.getSL()==null||!s.valCount()){
			return s;
		}
		if(!inner.isAto() && cs.getSL().isAto()){
			if((cs.getSL().getValue()==0)||(cs.getSL().getValue()==1)||(cs.getSR().getValue()==1)||(cs.getSR().getValue()==1)){
				return s;
			}
			if(((inner.getOpr()==0 && cs.getOpr()==1))||((inner.getOpr()==1 && cs.getOpr()==0))){
				Sentence s1 = cs.getSL();
				Sentence s2L = null;
				Sentence s2R = null;
				if(inner.getSL().isAto()){
					s2L = new Sentence(inner.getSL().getValue(),inner.getSL().getNot());
				}else{
					s2L = inner.getSL();
				}
				if(inner.getSR().isAto()){
					s2R = new Sentence(inner.getSR().getValue(),inner.getSR().getNot());
				}else{
					s2R = inner.getSR();
				}
				Sentence s3 = new Sentence(s1,s2L,cs.getOpr(),false);
				Sentence s4 = new Sentence(s1,s2R,cs.getOpr(),false);
				Sentence s5 = new Sentence(s3,s4,inner.getOpr(),false);
				if(s3.checkAtoValues()){
					everySent = new ArrayList<SentSequence>();
					arrayCleared=true;
				}else if(s4.checkAtoValues()){
					everySent = new ArrayList<SentSequence>();
					arrayCleared=true;
				}
				return s5;
				
			}
		}
		return s;
	}
	
	public static Sentence rule67ab(Sentence s) throws CloneNotSupportedException{
		if(!s.isAto()){
			if(s.getSL().checkSent(s.getSR())&&(s.getOpr()==1||s.getOpr()==0)){
				//caso as duas sejam iguais, retorna uma delas
				return (Sentence) s.getSL().clone();
			}
			if(s.getSR().isAto()){
				switch(s.getSR().getValue()){				
				case 0:
					if(s.getOpr()==0){
						everySent = new ArrayList<SentSequence>();
						arrayCleared=true;
						return (Sentence) s.getSL().clone();
					}else if(s.getOpr()==1){
						//TODO aqui p ^ F <=> F
						everySent = new ArrayList<SentSequence>();
						arrayCleared=true;
						return (Sentence) s.getSR().clone();
					}
					break;
				case 1:
					if(s.getOpr()==1){
						everySent = new ArrayList<SentSequence>();
						arrayCleared=true;
						return (Sentence) s.getSL().clone();
					}else if(s.getOpr()==0){
						//TODO aqui: p v V <=> V
						everySent = new ArrayList<SentSequence>();
						arrayCleared=true;
						
						return (Sentence) s.getSR().clone();
					}
					break;
				default:
					break;
				}
			}
			if(s.getSL().isAto()){
				switch(s.getSL().getValue()){				
				case 0:
					if(s.getOpr()==0){
						//F v p <=> p
						everySent = new ArrayList<SentSequence>();
						arrayCleared=true;
						return (Sentence) s.getSR().clone();
					}else if(s.getOpr()==1){
						//TODO aqui F ^ p <=> F
						everySent = new ArrayList<SentSequence>();
						arrayCleared=true;
						return (Sentence) s.getSL().clone();
					}
					break;
				case 1:
					if(s.getOpr()==1){
						//V ^ p <=> p
						everySent = new ArrayList<SentSequence>();
						arrayCleared=true;	
						return (Sentence) s.getSR().clone();
					}else if(s.getOpr()==0){
						//TODO aqui: V v p <=> V
						everySent = new ArrayList<SentSequence>();
						arrayCleared=true;	
						return (Sentence) s.getSL().clone();
					}
					break;
				default:
					break;
				}
			}
		}
		return s;
	}
	
	public static Sentence rule8ab(Sentence s) throws CloneNotSupportedException{
		Sentence cs = (Sentence) s.clone();
		if(cs.getSL()==null||cs.getSR()==null){
			return s;
		}
		if(cs.getSR().isAto() && cs.getSL().isAto()){
			Sentence asL = (Sentence)cs.getSL();
			Sentence asR = (Sentence)cs.getSR();
			Sentence newAs= null;
			if(asL.getValue()==asR.getValue()){
				if((asL.checkSent(asR))){
//					s.print();
					//se forem iguais retorna uma delas
					everySent = new ArrayList<SentSequence>();
					arrayCleared=true;
					return asL;
				}
				int value=0;
				if(cs.getOpr()==0){
					value=1;
				}
				//TODO aqui
				everySent = new ArrayList<SentSequence>();
				arrayCleared=true;
				newAs = new Sentence(value,false);
				return newAs;
			}
		}
		return s;
	}
	
	public static Sentence rule11(Sentence s) throws CloneNotSupportedException{
		Sentence cs = (Sentence) s.clone();
		if(cs.getOpr()==2){
			cs.setSL(cs.getSL().negate());
			cs.setSR(cs.getSR().negate());
			cs.setOpr(1);
			cs=cs.swap();
			cs.setOpr(2);
			return cs;
		}
		return s;
	}
}
