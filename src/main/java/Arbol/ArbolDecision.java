/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package Arbol;

import java.util.logging.*;
/**
 *
 * @author Master
 */
public class ArbolDecision {

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
        
        System.out.println("\n6.1 Instancias de Soleado");
        System.out.println("\t#\tC1\tC2");
        System.out.println("Soleado\t"+instSoleado+"\t"+noJugarSoleado+"\t"+jugarSoleado);
        System.out.println("Nublado\t"+instNublado+"\t"+noJugarNublado+"\t"+jugarNublado);
        System.out.println("Lluvias\t"+instLluvias+"\t"+noJugarLluvias+"\t"+jugarLluvias);
        
        
        //---Entropias de Estado General----
        //---SOLEADO
        
        
        
        float p1Soleado = ValorP( noJugarSoleado, instSoleado );
        float p2Soleado = ValorP( jugarSoleado, instSoleado );
        float imSoleado = Entropia( p1Soleado, p2Soleado );
        
        float p1Nublado = ValorP( noJugarNublado, instNublado );
        float p2Nublado = ValorP( jugarNublado, instNublado );
        float imNublado = Entropia( p1Nublado, p2Nublado );
        
        float p1Lluvias = ValorP( noJugarLluvias, instLluvias );
        float p2Lluvias = ValorP( jugarLluvias, instLluvias );
        float imLluvias = Entropia( p1Lluvias, p2Lluvias );
        
        System.out.println("\n6.2 Calcular Entropia de Estado General");
        imprimirEntropia( "Soleado", p1Soleado, p2Soleado, imSoleado );
        imprimirEntropia( "Nublado", p1Nublado, p2Nublado, imNublado );
        imprimirEntropia( "Lluvias", p1Lluvias, p2Lluvias, imLluvias );
        imprimirEntropia( "Soleado", p1Soleado, p2Soleado, imSoleado );
        
        // Imprimir medias
        /*System.out.println(mediaTempGeneral);
        System.out.println(mediaTempSoleado);
        System.out.println(mediaTempNublado);
        System.out.println(mediaTempLluvias); */
        
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
        System.out.println("\tTemperatura");
        System.out.println("Media General "+ mediaTempGeneral);
        System.out.println("Menor Media: " + menorMediaTemp + " C1 = " + noJugarMenorTemp + " C2 = " + jugarMenorTemp);
        System.out.println("Mayor Media: " + mayorMediaTemp + " C1 = " + noJugarMayorTemp + " C2 = " + jugarMayorTemp);
        
        System.out.println("");
        System.out.println("\tHumedad");
        System.out.println("Media General "+ mediaHumedadGeneral);
        System.out.println("Menor Media: " + menorMediaHumedad + " C1 = " + noJugarMenorHumedad + " C2 = " + jugarMenorHumedad);
        System.out.println("Mayor Media: " + mayorMediaHumedad + " C1 = " + noJugarMayorHumedad + " C2 = " + jugarMayorHumedad);
        
        System.out.println("");
        System.out.println("\tViento");
        System.out.println("Si: " + contVientoSi + " C1 = " + noJugarVientoSi + " C2 = " + jugarVientoSi );
        System.out.println("No: " + contVientoNo + " C1 = " + noJugarVientoNo + " C2 = " + jugarVientoNo );
        
        // Entropia de 
        
        
        // Entropia de Temperatura
        
        
    }
    
    public static void imprimirEntropia( String nombre, float p1, float p2, float e ) {
        
        System.out.println(nombre);
        System.out.println("\tP1: "+p1);
        System.out.println("\tP2: "+p2);
        System.out.println("\tEntropia: "+e);
    }
    //--Metodo para determinar el valor de P--
    public static float ValorP( int n, int nm ){       
        return (float)n/(float)nm;
        
    }
    
    public static float Entropia(float P1, float P2){
        //double Im = - ((P1*(Math.log(P1)/Math.log(2))) + (P2*(Math.log(P2)/Math.log(2))));
        
        float e = (float)-((P1*(Math.log(P1)/Math.log(2))) + (P2*(Math.log(P2)/Math.log(2))));
        
        if (Double.isNaN(e))
            e = 0;
        return e;
    }
}
   