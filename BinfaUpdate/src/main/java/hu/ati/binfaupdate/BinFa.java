package hu.ati.binfaupdate;

import java.io.PrintWriter;

public class BinFa {
    private Csomopont fa;
    private StringBuilder os;
    //private Csomopont gyoker=new Csomopont();
    private Csomopont gyoker;
    private int melyseg, atlagosszeg, atlagdb, maxMelyseg;
    private double szorasosszeg, szoras, atlag;
    //private Csomopont ujCsomopont;
    public BinFa(){
	gyoker=new Csomopont();
	fa=gyoker;
    }
    public void addBit(char bit){
	if(bit=='0'){
            if(fa.getNullas()==null){
		Csomopont ujCsomopont=new Csomopont('0');
		fa.setNulla(ujCsomopont);
		fa=gyoker;
            }else {
		fa=fa.getNullas();
            }
	}else {
            if(fa.getEgyes()==null){
		Csomopont ujCsomopont=new Csomopont('1');
		fa.setEgyes(ujCsomopont);
		fa=gyoker;
            }else {
                fa=fa.getEgyes();
            }
        }
    }
    public String kiir() {
	melyseg=0;
	os=new StringBuilder();
	kiir(gyoker, new PrintWriter(System.out));
       return os.toString();
    }
    public void kiir(Csomopont elem, PrintWriter out){
	//System.out.println("kiir(Csomopont elem)");
	if(elem != null){
            melyseg++;
            //System.out.println("melyseg: "+melyseg);
            kiir(elem.getEgyes(),out);
            for(int i=0; i<melyseg; i++){
		//System.out.print("---");
                
                os.append("---");
            }
            out.print(elem.getJelenlegi()+" ("+String.valueOf(melyseg - 1)+")</br>");
            //System.out.print(elem.getJelenlegi()+" ("+String.valueOf(melyseg - 1)+")\n");
            os.append(elem.getJelenlegi());
            os.append("(").append(String.valueOf(melyseg - 1)).append(")</br>");
            os.append("\n");
				
            kiir(elem.getNullas(),out);
		melyseg--;
	}
    }
    public int getMelyseg(){
	melyseg=maxMelyseg=0;
	rmelyseg(gyoker);
	return maxMelyseg-1;
    }
    public double getAtlag() {
	melyseg = atlagosszeg = atlagdb = 0;
	ratlag(gyoker);
	atlag = ((double) atlagosszeg) / atlagdb;
	return atlag;
    }
		
    public double getSzoras() {
	atlag = getAtlag();
	szorasosszeg = 0.0;
	melyseg = atlagdb = 0;
        rszoras(gyoker);
	if (atlagdb - 1 > 0) {
            szoras = Math.sqrt(szorasosszeg / (atlagdb - 1));
	} else {
            szoras = Math.sqrt(szorasosszeg);
	}
	return szoras;
    }
    public void rmelyseg(Csomopont elem) {
	if (elem != null) {
            melyseg++;
            if (melyseg > maxMelyseg) {
		maxMelyseg = melyseg;
            }
            rmelyseg(elem.getNullas());
            rmelyseg(elem.getEgyes());
            melyseg--;
	}
    }
    public void ratlag(Csomopont elem) {
	if (elem != null) {
            melyseg++;
            ratlag(elem.getNullas());
            ratlag(elem.getEgyes());
            melyseg--;
            if (elem.getEgyes() == null && elem.getNullas() == null) {
                atlagdb++;
		atlagosszeg += melyseg;
            }
	}
    }
    public void rszoras(Csomopont elem) {
	if (elem != null) {
            melyseg++;
            rszoras(elem.getNullas());
            rszoras(elem.getEgyes());		  
            melyseg--;
            if ((elem.getEgyes() == null) && (elem.getNullas() == null)){
                atlagdb++;
                szorasosszeg += ((melyseg - atlag) * (melyseg - atlag));
            }
	}
    }
}

