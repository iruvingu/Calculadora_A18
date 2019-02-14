package com.example.scott.calculadora_a18;

import java.util.Stack;
/*Clase para convertir la cadena infija a posfija*/
public class Convierte {
    private String[] cadena;
    //private String aux;

    public Convierte(String cadena){
        //this.cadena = cadena.split(" ");
        this.cadena = cadena.split("(?<=[-+*///()])|(?=[-+*///()])");

    }

    public String[] getCadena() {	return cadena;
    }
    public void setCadena(String[] cadena) {	this.cadena = cadena;
    }

    public String inf_posf() {
        String[] exp = this.cadena;
        Stack<String> pila = new Stack<String>();
        String postfix = "";                                // Inicializar el String postfijo final
        for (int i=0;i<exp.length;i++){                   //Buscar en todos los elementos del array exp
            if (!esOperando(exp[i]) && !esParentesisDer(exp[i])){
                while (!pila.empty()
                        && !pila.peek().equals("(")
                        && tieneMayorPrioridad(pila.peek(), exp[i])){
                    postfix += " " + pila.peek();
                    pila.pop();
                }
                pila.push(exp[i]);
            }
            else if (esOperando(exp[i])) {
                postfix += " " + exp[i];
            }
            else if (esParentesisDer(exp[i])){
                while (!pila.empty() && !pila.peek().equals("(")){
                    postfix += " " + pila.peek(); pila.pop();
                }
                pila.pop();
            }
        }
        while (!pila.empty()){
            postfix += " " + pila.peek(); pila.pop();
        }
        return postfix;
    }

    public boolean esParentesisDer(String Parent){
        String parentesis = ")";
        if (Parent.compareTo(parentesis) == 0)
            return true;
        else return false;
    }

    public boolean esOperando(String Op){
        try
        {
            Float.parseFloat(Op);
            return true;
        }
        catch(NumberFormatException e)
        {
            //not a Float
            return false;
        }
    }

    public boolean tieneMayorPrioridad(String op1, String op2){
        int op1Weight = getPesoOperador(op1);
        int op2Weight = getPesoOperador(op2);
        if ((op1Weight == op2Weight) && (op1Weight != 3)){
            return true;
        }else if ((op1Weight == 3) && (op2Weight == 3)) {
            return false;
        }
        return op1Weight>op2Weight? true : false;
    }

    public int getPesoOperador(String op){
        int weight = -1;
        switch (op){

            case "+":
            case "-":
                weight = 1; break;
            case "*":
            case "/":
            case "sin":
            case "cos":
            case "tan":
            case "cot":
            case "sec":
            case "csc":
                weight = 2; break;
            case "^":
                weight = 3; break;
            case "(":
                weight = 4; break;
        }
        return weight;
    }

}
