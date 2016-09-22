
public class SentSequence implements Cloneable{
	Sentence thisSent;
	SentSequence prevSent;
	int rule;
	boolean expanded = false;
	boolean expandedIn = false;
	
	public SentSequence(Sentence s1, SentSequence s2, int i){
		this.thisSent = s1;
		this.prevSent = s2;
		this.rule = i;
	}

	public Sentence getThis() {
		if(this.thisSent!=null){
			return this.thisSent;
		}else{
			return null;
		}
	}

	public SentSequence getPrev() {
		if(this.prevSent==null){
			return null;
		}
		return this.prevSent;
	}
	
	public void printSent(){
		SentSequence aux;
		aux = this;
		while(aux!=null){
			System.out.print(aux.getRule()+": ");
			aux.getThis().print();
			aux=aux.getPrev();
		}
	}
	
	@Override
	public SentSequence clone() throws CloneNotSupportedException{
		return (SentSequence) super.clone();
	}
	
	public void expand(){
		this.expanded=true;
	}
	public boolean isExpanded(){
		return this.expanded;
	}
	public void expandIn(){
		this.expandedIn=true;
	}
	public boolean isExpandedIn(){
		return this.expandedIn;
	}

	public int getRule() {
		return this.rule;
	}

}
