package com.example.calculadoragrafica.MathEngine;

import java.util.ArrayList;

public class equacao {
    public String equacao_original;
    public String equacao2;

    public equacao(String new_equacao){
        this.equacao_original=new_equacao;
        this.equacao2=new_equacao.replace(" ","").replace("pi","3.14159265359").replace("e","2.718281828459045");
    }

    public String getEquacao_original() {
        return equacao_original;
    }

    public String getEquacao2() {
        return equacao2;
    }

    public double equacao2_to_number(){
        return Double.parseDouble(equacao2);
    }

    public boolean validar(){
        int rigth = 0, left = 0;
        for(char c : this.equacao2.toCharArray()){
            if (c == '(') left++;
            else if (c == ')') rigth++;
            if (rigth > left) return false;
        }
        if (rigth == left) return true;
        else return false;
    }

    public boolean isnumeric(){
        for(char c : this.equacao2.toCharArray())  if ((c < '0' || c > '9') && c != '.' && c != 'e') return false;
        return true;
    }


    public boolean isnumber(){
        try {
            Double.parseDouble(this.equacao2);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean ContaintPlusSub(){
        ArrayList<Integer> Parentheses = this.IndexBetweenParentheses();
        for(int i = 0; i < this.equacao2.length(); i++){
            char c = this.equacao2.charAt(i);
            if ((c == '+' || c == '-') && Parentheses.contains(i) == false)
                return true;
        }
        return false;
    }

    public int index(){
        for(int i = this.equacao2.length() - 1; i >= 0; i--){
            if (this.equacao2.charAt(i) == this.Sinal().toCharArray()[0] && this.IndexBetweenParentheses().contains(i) == false)
                return i;
        }
        return -1;
    }

    public ArrayList<Integer> IndexBetweenParentheses(){
        ArrayList<Integer> result = new ArrayList<>();
        int aux = 0;
        for (int i = 0; i < this.equacao2.length(); i++){
            if (this.equacao2.charAt(i) == '(')  aux++;
            if (this.equacao2.charAt(i) == ')')  aux --;
            if (aux > 0 && this.equacao2.charAt(i) != '(' && this.equacao2.charAt(i) != ')') result.add(i);
        }
        return result;
    }

    public String Sinal(){
        boolean PlusSub = this.ContaintPlusSub();
        if (this.equacao2.toCharArray()[0] == '(' && this.equacao2.toCharArray()[this.equacao2.length() - 1] == ')') return "()";
        ArrayList<Integer> IndexParentheses = this.IndexBetweenParentheses();
        for(int i = this.equacao2.length() - 1; i >= 0; i--){
            char c = this.equacao2.charAt(i);
            if (c == '+' && IndexParentheses.contains(i) == false) return "+";
            else if (c == '-' && IndexParentheses.contains(i) == false) return "-";
            else if (c == '*' && PlusSub == false && IndexParentheses.contains(i) == false) return "*";
            else if (c == '/' && PlusSub == false && IndexParentheses.contains(i) == false) return "/";
            else if (c == '^' && PlusSub == false && IndexParentheses.contains(i) == false) return "^";
            else if (c == '!' && PlusSub == false && IndexParentheses.contains(i) == false) return "!";
        }
        return " ";
    }

    public String Mathfunc(){
        if (this.equacao2.length() <= 4) return " ";
        String FuncSTR = this.equacao2.substring(0,4);
        String lnSTR = this.equacao2.substring(0, 3);
        String LastSTR = Character.toString(this.equacao2.charAt(this.equacao2.length() - 1));
        if (LastSTR.equals(")") && (this.IndexBetweenParentheses().size() == this.equacao2.length() - 4 || this.IndexBetweenParentheses().size() == this.equacao2.length() - 5)){
            if (FuncSTR.equals("sen(")) return "sen";
            else if (FuncSTR.equals("sin(")) return "sen";
            else if (FuncSTR.equals("cos(")) return "cos";
            else if (FuncSTR.equals("tan(")) return "tan";
            else if (FuncSTR.equals("log(")) return "log";
            else if (lnSTR.equals("ln(")) return "ln";
            else return " ";
        }   else return " ";
    }

    public int SinalNumber(){
        float sum = 0;
        for(char c : this.equacao2.toCharArray()){
            if (c == '+') sum++;
            else if (c == '-') sum++;
            else if (c == '*') sum++;
            else if (c == '/') sum++;
            else if (c == '^') sum++;
            else if (c ==  '!') sum++;
            else if (c == ')' || c == '(') sum = 0.5f + sum;
        }
        return (int) sum;
    }

    //Retorna o tipo da expressão:
    //numeric -> se a expressão for apenas um número;
    //MathFunc -> se a expressão for uma expressão matemática tipo sen, cons, tan, log;
    //Sinal -> se a expressão for uma operação composta por apenas um sinal (5+6; 8^2);
    //complex -> se a expressão tiver mais do que um dos elementos a cima
    public String getExpressionType(){
        if (this.isnumeric()) return "numeric";
        else if (this.Mathfunc() != " ") return "MathFunc";
        else if (this.Sinal() != " " && this.SinalNumber() == 1) return "Sinal";
        else return "complex";
    }

    public ArrayList<String> SplitExpression(String ExpressionType){
        ArrayList<String> StringSplit = new ArrayList<>();
        if (ExpressionType == "MathFunc"){
            String func = this.Mathfunc();
            StringSplit.add(this.equacao2.substring(func.length() + 1, this.equacao2.length() - 1));
            StringSplit.add(this.equacao2.substring(0,func.length() + 1) + ")");
            StringSplit.add("");
        }   else if(ExpressionType == "Sinal" || ExpressionType == "complex"){
            String sinal = this.Sinal();
            if (sinal != "()"){
                int SinalIndex = this.index();
                StringSplit.add(this.equacao2.substring(0, SinalIndex));
                StringSplit.add(sinal);
                StringSplit.add(this.equacao2.substring(SinalIndex + 1, this.equacao2.length()));
            }   else if (sinal == "()"){
                StringSplit.add(this.equacao2.substring(1, this.equacao2.length() - 1));
                StringSplit.add("()");
                StringSplit.add("");
            }
        }
        return StringSplit;
    }




}
