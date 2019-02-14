package com.example.scott.calculadora_a18;

import android.util.Log;

import java.util.Stack;
/*Clase para calcular la cadena posfija*/
public class Calcula {
	private String[] posfijo;
	
	public Calcula(){
	}

	public String[] getPosfijo() {
		return posfijo;
	}

	public void setPosfijo(String[] posfijo) {
		this.posfijo = posfijo;
	}

	public Calcula(String posfijo){
		this.posfijo = posfijo.split(" ");
	}
	
	public String calcularOperacion(){
		Stack<String> pila_numeros = new Stack<String>();
		String resultado = "";
		String derecha = "";
		float respuesta = 0;
		String izquierda = "";
			/*para cada Token
	 * 		if Token is float
	 * 			push token
	 * 		else if token is operator
	 * 			pop right-side value
	 * 			pop left-side value
	 * 			evaluate operator
	 * 			push result
	 * next
	 */
		for(int i=0; i < this.posfijo.length; i++){
			if(esFlotante(this.posfijo[i])){
				pila_numeros.push(this.posfijo[i]);
			}
			else if (!esFlotante(this.posfijo[i]) && !pila_numeros.empty()){
				if (!esTrigonometrica(this.posfijo[i])){
					derecha = "" + pila_numeros.peek(); pila_numeros.pop();
					izquierda = pila_numeros.peek(); pila_numeros.pop();
					respuesta = res(Float.parseFloat(izquierda),Float.parseFloat(derecha),this.posfijo[i]);
				}
				else{
					derecha = pila_numeros.pop();
					respuesta = res(0,Float.parseFloat(derecha),this.posfijo[i]);
				}
				resultado = Float.toString(respuesta);
				pila_numeros.push(resultado);
			}
			else{
				Log.d("Empty value",this.posfijo[i]);
			}
		}
		while(!pila_numeros.empty())
		resultado = pila_numeros.pop();
		
		return resultado;
		
	}
	private boolean esTrigonometrica(String string) {
		String sin = "sin", cos = "cos", tan = "tan", cot = "cot", sec = "sec", csc = "csc";
		if (string.equals(sin) || string.equals(cos) || string.equals(tan) || string.equals(cot)
				|| string.equals(sec) || string.equals(csc)){
			return true;
		}
		else
		return false;
	}

	public boolean esFlotante(String Op){
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
	public float res(float izq, float der,String operador){
		float r = 0;
		switch (operador){
		case "+":
			r = izq + der; break;
        case "-":
            r = izq - der; break;
        case "*":
        	r = izq * der; break;
        case "/":
        	r = izq / der; break;
        case "^":
        	r = (float) Math.pow(izq, der); break;
        case "sin":
        	r = (float) (Math.sin(der)); break;
        case "cos":
        	r = (float) (Math.cos(der)); break;
        case "tan":
        	r = (float) (Math.tan(der)); break;
        case "cot":
        	r = (float) (1.0 / Math.tan(der)); break;
        case "sec":
        	r = (float) (1.0 / Math.cos(der)); break;
        case "csc":
        	r = (float) (1.0 / Math.sin(der)); break;
		}
		return r;
	}


}
