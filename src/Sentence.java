public class Sentence implements Cloneable{
	private boolean not;
	private boolean isAto;
	private int opr; // 0: OR, 1: AND, 2: >, 3: <>
	private Sentence sRight;
	private Sentence sLeft;
	private int value=-1;
	public String ok;
	
	public Sentence(Sentence left, Sentence right, int opr, boolean not){
		this.isAto = false;
		this.not = not;
		this.opr = opr;
		this.sRight = right;
		this.sLeft= left;
		this.value = -1;
	}
	
	public Sentence(int v, boolean not){
		this.value = v;
		this.not=not;
		this.isAto=true;
	}
	
	public Sentence swap() throws CloneNotSupportedException{
		Sentence s = (Sentence) this.clone();
		if(!this.isAto()&& (s.getOpr()==0 || s.getOpr()==1)){
			s.sRight = this.sLeft;
			s.sLeft = this.sRight;
		}
		return s;
	}
	
	Sentence negateWImp() throws CloneNotSupportedException{
		Sentence cs = (Sentence) this.clone();
		cs.opr=1;
		cs.sLeft=cs.sLeft.negate().negate();
		cs.sRight=cs.sRight.negate();
		return cs;
	}
	
	Sentence negateWBImp() throws CloneNotSupportedException{
		Sentence cs = (Sentence) this.clone();
		cs.opr = 1;
		cs.sRight=cs.sRight.negate();
		Sentence csr = new Sentence(this.sRight.clone(),this.sLeft.clone().negate(),1,false);		
		Sentence s = new Sentence(cs,csr,0,false);
		return s;
	}
	
	public Sentence negate() throws CloneNotSupportedException{
		Sentence s = (Sentence) this.clone();
		if(!isAto){
			if(opr==2){
				s=s.negateWImp();					
			}else if(opr==3){
				s=s.negateWBImp();			
			}else{
				s.opr = this.opr^1;
				s.sLeft=s.sLeft.negate();
				s.sRight=s.sRight.negate();
			}
		}else{
			if(this.getValue()==1||this.getValue()==0){
				s.value=(1-s.getValue());
			}else{
				s.not=!s.not;
			}
			
		}
		return s;
	}
	
	public void print(){
		System.out.println(this.toString());
	}
	
	public String toString(){
		String str = "";
		if(this.not){
			str+="~";
		}
		if(this.isAto()){
			String aux = "";
			switch(this.value){
				case 0:
					aux+='F';
					break;
				case 1:
					aux+='T';
					break;
				case 2:
					aux+='A';
					break;
				case 3:
					aux+='B';
					break;
				case 4:
					aux+='C';
					break;
				case 5:
					aux+='D';
					break;
				case 6:
					aux+='S';
					break;
				case 7:
					aux+='R';
					break;
				case 8:
					aux+='X';
					break;
				case 9:
					aux+='P';
					break;
				case 10:
					aux+='T';
					break;
			}
			str+=aux;
		}else{
			str+="(";
			str+=this.sLeft.toString();
			switch(this.opr){
				case 0:
					str+="v";
					break;
				case 1:
					str+="^";
					break;
				case 2:
					str+=">";
					break;
				case 3:
					str+="<>";
					break;
				default:
					break;
			}
			str+=this.sRight.toString();
			str+=")";
		}
		return str;
	}
	
	public Sentence getLastL(){
		Sentence aux = this;
		while(!aux.getSL().isAto()){
			aux = aux.getSL();
		}
		return aux;
	}
	
	public int innerCount(){
		int i=0;
		Sentence aux = this;
		while(!aux.getSL().isAto()){
			i++;
			aux = aux.getSL();
		}
		return i;
	}

	public void setInnerSL(Sentence s, int i) {
		//s: senten�a que quero inserir
		Sentence aux = this;
		while(!aux.getSL().isAto()&&i>1){
			i--;
			aux = aux.getSL();
		}
		if(!aux.checkSent(s)){
			aux.setSL(s);
		}		
	}
	
	public boolean checkSent(Sentence s2){
		if(this.toString().equals(s2.toString())){
			return true;
		}
		return false;
	}
	
	
	public Sentence clone() throws CloneNotSupportedException{
		Sentence s = (Sentence) super.clone();
		if(!s.isAto()){
			if(!s.sLeft.isAto()){
				s.sLeft = new Sentence(sLeft.sLeft.clone(),sLeft.sRight.clone(),sLeft.opr,sLeft.not);
			}
			if(!s.sRight.isAto()){
				s.sRight = new Sentence(sRight.sLeft.clone(),sRight.sRight.clone(),sRight.opr,sRight.not);
			}			
		}
		return s;
	}

	public int getOpr() {
		return this.opr;
	}

	public void setOpr(int i) {
		this.opr=i;		
	}
	public void setSR(Sentence s){
		this.sRight = s;
	}
	public void setSL(Sentence s){
		this.sLeft= s;
	}
	public Sentence getSR(){
		return this.sRight;
	}
	public Sentence getSL(){
		return this.sLeft;
	}

	public boolean isAto() {
		return this.isAto;
	}

	public int getValue() {
		if(this.isAto()){
			return this.value;
		}
		return -1;
	}
	public boolean getNot() {
		return this.not;
	}
}
