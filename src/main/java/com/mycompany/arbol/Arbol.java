
package com.mycompany.arbol;

import java.util.logging.*;
/**
 *
 * @author Master
 */
public class Arbol {

    public static void main(String[] args) {
        //--Registros--   
        String[] campos = {"#Inst", "Estados", "Temp", "Humedad", "Viento", "Clase"};
        String[][] registros = {
            {"1", "soleado", "85", "85", "no", "no jugar"},
            {"2", "soleado", "80", "90", "si", "no jugar"},
            {"3", "nublado", "83", "78", "no", "jugar"},
            {"4", "lluvias", "70", "96", "no", "jugar"},
            {"5", "lluvias", "68", "80", "no", "jugar"},
            {"6", "lluvias", "65", "70", "si", "no jugar"},
            {"7", "nublado", "64", "65", "si", "jugar"},
            {"8", "soleado", "72", "95", "no", "no jugar"},
            {"9", "soleado", "69", "70", "no", "jugar"},
            {"10", "lluvias", "75", "80", "no", "jugar"},
            {"11", "soleado", "75", "70", "si", "jugar"},
            {"12", "nublado", "72", "90", "si", "jugar"},
            {"13", "nublado", "81", "75", "no", "jugar"},
            {"14", "lluvias", "71", "80", "si", "no jugar"}};
        
        //--Colocacion de campos--
        for (int x = 0; x < campos.length; x++){
            System.out.print(campos[x]+"\t");
        }
        System.out.println();
        //--Colocación de registros--
        for (int i = 0; i < registros.length; i++) {
            for (int j = 0; j < registros[i].length; j++) {
                System.out.print(registros[i][j]+"\t");
            }
            System.out.println();
        } 
        System.out.println("\n1. No. Instancias: " + registros.length);
        
        String c1="no jugar";
        String c2="jugar";

        int contJugar=0;
        int contNoJugar=0;

        System.out.println("\n2. Clases");
        System.out.println("\tC1: " + c1 + "\n\tC2: " + c2);

        for (int i = 0; i <= 13; i++){
            if(registros[i][5].equals("no jugar")){
                contNoJugar ++;
            }
        }
        contJugar = 14 - contNoJugar;

        System.out.println("\n3. Instancias por clase");
        System.out.println("\tN1: " + contNoJugar + "\n\tN2: " + contJugar);

        float P1 = ValorP( contNoJugar, 14 );
        float P2 = ValorP( contJugar, 14 );
    
        System.out.println("4. Determinar el valor de P: ");
        System.out.println("\tP1: "+P1+"\n\tP2: "+P2);
        
        float IM = Entropia(P1,P2);
        System.out.println("5. Determinar Entropia: ");
        System.out.println("\tIM: "+IM);
        
        //---ESTADO GENERAL----}
        float mediaTempGeneral = 0;
        float mediaHumedadGeneral = 0;
        
        int instSoleado=0;
        int noJugarSoleado = 0;
        int jugarSoleado = 0;
        float mediaTempSoleado = 0;
        float mediaHumedadSoleado = 0;
        
        int instLluvias=0;
        int noJugarLluvias = 0;
        int jugarLluvias = 0;
        float mediaTempLluvias = 0;
        float mediaHumedadLluvias = 0;
        
        int instNublado=0;
        int noJugarNublado = 0;
        int jugarNublado = 0;
        float mediaTempNublado = 0;
        float mediaHumedadNublado = 0;
        
        
        
        for (int i = 0; i <= 13; i++){
            
            // Calcula el promedio de Temperatura y Humedad General
            mediaTempGeneral += Integer.parseInt( registros[i][2] );
            mediaHumedadGeneral += Integer.parseInt( registros[i][3] );
            
            // Busca las instancias de Soleado y las Instancias de Clase
            if(registros[i][1].equals("soleado")){
                instSoleado++;
                mediaTempSoleado += Integer.parseInt(registros[i][2]);
                mediaHumedadSoleado += Integer.parseInt(registros[i][3]);
                
                if( registros[i][5].equals("jugar") )
                    jugarSoleado++;
            }
            
            // Busca las instancias de Nublado y las Instancias de Clase
            if(registros[i][1].equals("nublado")){
                instNublado++;
                mediaTempNublado += Integer.parseInt(registros[i][2]);
                mediaHumedadNublado += Integer.parseInt(registros[i][3]);
                
                if( registros[i][5].equals("jugar") )
                    jugarNublado++;
            }
            
            // Busca las instancias de Lluvioso y las instancias de Clase
            if(registros[i][1].equals("lluvias")){
                instLluvias++;
                mediaTempLluvias += Integer.parseInt(registros[i][2]);
                mediaHumedadLluvias += Integer.parseInt(registros[i][3]);
                
                if( registros[i][5].equals("jugar") )
                    jugarLluvias++;
            }
        }
        
        // Saca el promedio de las sumas
        mediaTempGeneral = Math.round( mediaTempGeneral / 14 );
        mediaHumedadGeneral = Math.round( mediaHumedadGeneral / 14 );
        
        mediaTempSoleado = Math.round( mediaTempSoleado / instSoleado ); 
        mediaTempLluvias = Math.round( mediaTempLluvias / instLluvias );
        mediaTempNublado = Math.round( mediaTempNublado / instNublado );
        
        mediaHumedadSoleado = Math.round( mediaHumedadSoleado / instSoleado );
        mediaHumedadLluvias = Math.round( mediaHumedadLluvias / instLluvias );
        mediaHumedadNublado = Math.round( mediaHumedadNublado / instNublado );
        
        noJugarSoleado = instSoleado - jugarSoleado;
        noJugarNublado = instNublado - jugarNublado;
        noJugarLluvias = instLluvias - jugarLluvias;
        
        System.out.println("\n6.1 Instancias de Estado General");
        System.out.println("\t#\tC1\tC2");
        System.out.println("Soleado\t"+instSoleado+"\t"+noJugarSoleado+"\t"+jugarSoleado);
        System.out.println("Nublado\t"+instNublado+"\t"+noJugarNublado+"\t"+jugarNublado);
        System.out.println("Lluvias\t"+instLluvias+"\t"+noJugarLluvias+"\t"+jugarLluvias);
        
        
        //---Entropias de Estado General----
        //---SOLEADO 
        float p1Soleado = ValorP( noJugarSoleado, instSoleado );
        float p2Soleado = ValorP( jugarSoleado, instSoleado );
        float imSoleado = Entropia( p1Soleado, p2Soleado );
        //---NUBLADO
        float p1Nublado = ValorP( noJugarNublado, instNublado );
        float p2Nublado = ValorP( jugarNublado, instNublado );
        float imNublado = Entropia( p1Nublado, p2Nublado );
        //---LLUVIOSO
        float p1Lluvias = ValorP( noJugarLluvias, instLluvias );
        float p2Lluvias = ValorP( jugarLluvias, instLluvias );
        float imLluvias = Entropia( p1Lluvias, p2Lluvias );
        //--Impuresa de la Division
        float imDivisionGeneral = ImpuresaDivisionGeneral(instSoleado, instNublado, instLluvias, imSoleado, imNublado, imLluvias);
        
        System.out.println("\n6.1.1 Entropia de Estado General");
        imprimirEntropia( "Soleado", p1Soleado, p2Soleado, imSoleado );
        imprimirEntropia( "Nublado", p1Nublado, p2Nublado, imNublado );
        imprimirEntropia( "Lluvias", p1Lluvias, p2Lluvias, imLluvias );

        // Entropia de Temperatura
        int mayorMediaTemp = 0;
        int menorMediaTemp = 0;
        int jugarMenorTemp = 0;
        int noJugarMenorTemp = 0;
        int jugarMayorTemp = 0;
        int noJugarMayorTemp = 0;
        
        int mayorMediaHumedad = 0;
        int menorMediaHumedad = 0;
        int jugarMenorHumedad = 0;
        int noJugarMenorHumedad = 0;
        int jugarMayorHumedad = 0;
        int noJugarMayorHumedad = 0;
        
        int contVientoSi = 0;
        int contVientoNo = 0;
        int jugarVientoSi = 0;
        int noJugarVientoSi = 0;
        int jugarVientoNo = 0;
        int noJugarVientoNo = 0;
        
        for( int i = 0; i <= 13; i++ ) {
            
            if( Integer.parseInt( registros[i][2]) < mediaTempGeneral ){
                //System.out.println("temp[" + i + "]: "+ registros[i][2]);
                menorMediaTemp++;
                if( registros[i][5].equals( "jugar" )){
                    jugarMenorTemp++;
                }    
            } else {
                mayorMediaTemp++;
                if( registros[i][5].equals( "jugar" )){
                    jugarMayorTemp++;
                }
            }
            if( Integer.parseInt( registros[i][3]) < mediaHumedadGeneral ){
                menorMediaHumedad++;
            
                if( registros[i][5].equals( "jugar" )){
                    jugarMenorHumedad++;
                }
            } else {
                mayorMediaHumedad++;
                if( registros[i][5].equals( "jugar" )){
                    jugarMayorHumedad++;
                }
            }
            if( registros[i][4].equals( "si" )){
                contVientoSi++;   
                if( registros[i][5].equals("jugar")) {
                    jugarVientoSi++;
                }
            } else {
                contVientoNo++;
                if( registros[i][5].equals("jugar")) {
                    jugarVientoNo++;
                }
            }
            
        } // Fin del for 
        noJugarMenorTemp = menorMediaTemp - jugarMenorTemp;
        noJugarMayorTemp = mayorMediaTemp - jugarMayorTemp;
        
        noJugarMenorHumedad = menorMediaHumedad - jugarMenorHumedad;
        noJugarMayorHumedad = mayorMediaHumedad - jugarMayorHumedad;
        
        noJugarVientoSi = contVientoSi - jugarVientoSi;
        noJugarVientoNo = contVientoNo - jugarVientoNo;
      
        System.out.println("");
        System.out.println("6.2 Instancias de Temperatura");
        imprimirInstanciaContinua( mediaTempGeneral, menorMediaTemp, mayorMediaTemp,
                noJugarMenorTemp, jugarMenorTemp, noJugarMayorTemp, jugarMayorTemp );
       
        //---Entropias de Temperatura----
        //--Menor a la media
        float p1MenorTemp = ValorP( noJugarMenorTemp, menorMediaTemp );
        float p2MenorTemp = ValorP( jugarMenorTemp, menorMediaTemp );
        float imMenorTemp = Entropia( p1MenorTemp, p2MenorTemp );
        //--Mayor a la media
        float p1MayorTemp = ValorP( noJugarMayorTemp, mayorMediaTemp );
        float p2MayorTemp = ValorP( jugarMayorTemp, mayorMediaTemp );
        float imMayorTemp = Entropia( p1MayorTemp, p2MayorTemp );
        //--Impuresa de la Division
        float imDivisionTemp = ImpuresaDivision(menorMediaTemp, mayorMediaTemp, imMenorTemp, imMayorTemp);
        
        System.out.println("\n6.2.1 Entropia de Temperatura");
        imprimirEntropia( "< "+mediaTempGeneral+"(MT)", p1MenorTemp, p2MenorTemp, imMenorTemp );
        imprimirEntropia( ">= "+mediaTempGeneral+"(MT)", p1MayorTemp, p2MayorTemp, imMayorTemp );
        
        System.out.println("");
        System.out.println("6.3 Instancias de Humedad");
        imprimirInstanciaContinua(mediaHumedadGeneral, menorMediaHumedad, mayorMediaHumedad, 
                noJugarMenorHumedad, jugarMenorHumedad, noJugarMayorHumedad, jugarMayorHumedad);
        
        //---Entropias de Humedad----
        //--Menor a la media
        float p1MenorHumedad = ValorP( noJugarMenorHumedad, menorMediaHumedad );
        float p2MenorHumedad = ValorP( jugarMenorHumedad, menorMediaHumedad );
        float imMenorHumedad = Entropia( p1MenorHumedad, p2MenorHumedad );
        //--Mayor a la media
        float p1MayorHumedad = ValorP( noJugarMayorHumedad, mayorMediaHumedad );
        float p2MayorHumedad = ValorP( jugarMayorHumedad, mayorMediaHumedad );
        float imMayorHumedad = Entropia( p1MayorHumedad, p2MayorHumedad );
         //--Impuresa de la Division
        float imDivisionHumedad = ImpuresaDivision(menorMediaHumedad, mayorMediaHumedad, imMenorHumedad, imMayorHumedad);
        
        System.out.println("\n6.3.1 Entropia de Humedad");
        imprimirEntropia( "< "+mediaHumedadGeneral+"(MH)", p1MenorHumedad, p2MenorHumedad, imMenorHumedad );
        imprimirEntropia( ">= "+mediaHumedadGeneral+"(MH)", p1MayorHumedad, p2MayorHumedad, imMayorHumedad );
        
        System.out.println("");
        System.out.println("6.4 Instancias de Viento");
        imprimirInstanciaViento( contVientoSi, contVientoNo, noJugarVientoSi, 
                noJugarVientoNo, jugarVientoSi, jugarVientoNo );

        //---Entropias de Viento----
        //--Menor a la media
        float p1MenorViento = ValorP( noJugarVientoSi, contVientoSi );
        float p2MenorViento = ValorP( jugarVientoSi, contVientoSi );
        float imMenorViento = Entropia( p1MenorViento, p2MenorViento );
        //--Mayor a la media
        float p1MayorViento = ValorP( noJugarVientoNo, contVientoNo );
        float p2MayorViento = ValorP( jugarVientoNo, contVientoNo );
        float imMayorViento = Entropia( p1MayorViento, p2MayorViento );
        //--Impuresa de la Division
        float imDivisionViento = ImpuresaDivision(contVientoSi, contVientoNo, imMenorViento, imMayorViento);
        
        
        System.out.println("\n6.4.1 Entropia de Viento");
        imprimirEntropia( "Si", p1MenorViento, p2MenorViento, imMenorViento);
        imprimirEntropia( "No", p1MayorViento, p2MayorViento, imMayorViento);
      
        System.out.println("\n6.5 Impuresa de Division");
        imprimirImpuresaDivision( "Estado General", imDivisionGeneral );
        imprimirImpuresaDivision( "Temperatura", imDivisionTemp );
        imprimirImpuresaDivision( "Humedad", imDivisionHumedad );
        imprimirImpuresaDivision( "Viento", imDivisionViento );
        
        //Frame frame = new Frame();
        //rame.setVisible(true);
        //frame.setEstadoGeneral("Estado General");
        TreeDemo demo = new TreeDemo();
        
        demo.main(args);
        
    }
    
    public static void imprimirInstanciaContinua( float media, int n1, int n2, int n1c1, int n1c2, int n2c1, int n2c2) {
        System.out.println("Media General "+ media);
        System.out.println("\t\t#\tC1\tC2");
        System.out.println("<  "+media+"(MT)\t"+n1+"\t"+n1c1+"\t"+n1c2);
        System.out.println(">= "+media+"(MT)\t"+n2+"\t"+n2c1+"\t"+n2c2);
    }
            
    public static void imprimirInstanciaViento( int n1, int n2, int n1c1, int n1c2, int n2c1, int n2c2) {
        System.out.println("\t#\tC1\tC2");
        System.out.println("Si\t"+n1+"\t"+n1c1+"\t"+n2c1);
        System.out.println("No\t"+n2+"\t"+n2c1+"\t"+n2c2);
    }
    
    public static void imprimirImpuresaDivision( String s, float e ){
        System.out.println("\tIM "+s+": "+e);    
    }
    
    public static void imprimirEntropia( String nombre, float p1, float p2, float e){
        System.out.println(nombre);
        System.out.println("\tP1: "+p1);
        System.out.println("\tP2: "+p2);
        System.out.println("\tIM: "+e);
        
    }
    
    //--Metodo para determinar el valor de P--
    public static float ValorP( int n, int nm ){       
        return (float)n/(float)nm;    
    }
    
    //--Metodo para determinar el valor de la etropia--
    public static float Entropia(float P1, float P2){
        float e = (float)-((P1*(Math.log(P1)/Math.log(2))) + (P2*(Math.log(P2)/Math.log(2))));
        if (Double.isNaN(e))
            e = 0;
        return e;
    }
    //--Metodo para determinar el valor de la Impuresa--
    public static float ImpuresaDivision(float I1, float I2, float P1,float P2){
            float e = (float)( (I1 / (I1+I2) * P1) + (I2 / (I2+I1) * P2) );
            if (Double.isNaN(e))
                e = 0;
            return e;
        }
    //--Metodo para determinar el valor de la Impuresa--
    public static float ImpuresaDivisionGeneral(float I1, float I2, float I3,float P1,float P2, float P3){
            float e = (float)( (I1/(I1+I2+I3)*P1) + (I2/(I2+I1+I3)*P2) + (I3/(I3+I1+I2)*P3));
            if (Double.isNaN(e))
                e = 0;
            return e;
        }

}