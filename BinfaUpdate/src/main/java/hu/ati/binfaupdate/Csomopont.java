package hu.ati.binfaupdate;

public class Csomopont {
    private Csomopont jobb=null;
    private Csomopont bal=null;
    private char jelenlegi;
    public Csomopont(){
        this('/');
    }
    public Csomopont(char csomo){
        this.jelenlegi=csomo;
	this.jobb=null;
	this.bal=null;
    }
    public Csomopont getEgyes(){
        return jobb;
    }
    public Csomopont getNullas(){
        return bal;
    }
    public char getJelenlegi(){
        return jelenlegi;
    }
    public void setNulla(Csomopont nulla){
	this.bal=nulla;
			
    }
    public void setEgyes(Csomopont egy){
	this.jobb=egy;			
    }
}
