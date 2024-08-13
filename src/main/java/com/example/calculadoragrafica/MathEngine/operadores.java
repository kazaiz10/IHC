package com.example.calculadoragrafica.MathEngine;

public class operadores {
    public double fatorial(double value){
        if(value==0){
            return 1;
        }else {
            return (value * fatorial(value-1));
        }
    }

    public Double Result(String operador, double x, double y){
        if (operador.equals("+")) return x + y;
        else if (operador.equals("-")) return x - y;
        else if (operador.equals("*")) return x * y;
        else if (operador.equals("/")) return x / y;
        else if (operador.equals("!")) return fatorial(x);
        else if (operador.equals("^")) return Math.pow(x, y);
        else if (operador.equals("sen()")) return Math.sin(x);
        else if (operador.equals("sin()")) return Math.sin(x);
        else if (operador.equals("cos()")) return Math.cos(x);
        else if (operador.equals("tan()")) return Math.tan(x);
        else if (operador.equals("ln()")) return Math.log(x);
        else if (operador.equals("()")) return x;
        return -1.0;
    }
}