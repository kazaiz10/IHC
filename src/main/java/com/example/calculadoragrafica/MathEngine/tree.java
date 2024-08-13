package com.example.calculadoragrafica.MathEngine;

import java.util.ArrayList;

public class tree {
    public tree node_direito;
    public tree node_esquerdo;
    public String this_node;

    public tree(String value) {
        this.node_direito = null;
        this.node_esquerdo = null;
        this.this_node = value;
    }

    public double getThis_node() {
        return Double.parseDouble(this_node);
    }

    public boolean folha(){
        if (node_direito==null && node_esquerdo==null)
            return true;
        else
            return false;
    }

    public void add(tree currentNode){
        if (currentNode != null){
            equacao e = new equacao(currentNode.this_node);
            if (e.isnumeric() == false){
                ArrayList<String> div = e.SplitExpression(e.getExpressionType());
                if (div.get(0) != ""){
                    currentNode.node_esquerdo = new tree(div.get(0));
                    add(currentNode.node_esquerdo);
                }
                currentNode.this_node = div.get(1);
                if (div.get(2) != ""){
                    currentNode.node_direito = new tree(div.get(2));
                    add(currentNode.node_direito);
                }
            }
        }
    }

    public void Resolver(tree currentNode){
        if (currentNode != null){
            equacao e = new equacao(currentNode.this_node);
            operadores cal = new operadores();
            if (e.isnumber() == false){
                if (currentNode.node_direito != null && currentNode.node_esquerdo != null){
                    equacao R = new equacao(currentNode.node_direito.this_node);
                    equacao L = new equacao(currentNode.node_esquerdo.this_node);
                    if (R.isnumber() && L.isnumber()){
                        currentNode.this_node = String.valueOf(cal.Result(currentNode.this_node, L.equacao2_to_number(), R.equacao2_to_number()));
                        currentNode.node_direito = null;
                        currentNode.node_esquerdo = null;
                    }
                }
                else if (currentNode.node_esquerdo != null && new equacao(currentNode.node_esquerdo.this_node).isnumber()){
                    equacao L = new equacao(currentNode.node_esquerdo.this_node);
                    currentNode.this_node = String.valueOf(cal.Result(currentNode.this_node, L.equacao2_to_number(), 0));
                    currentNode.node_direito = null;
                    currentNode.node_esquerdo = null;
                }
                else if (currentNode.node_direito != null && new equacao(currentNode.node_direito.this_node).isnumber()){
                    equacao R = new equacao(currentNode.node_direito.this_node);
                    currentNode.this_node = String.valueOf(cal.Result(currentNode.this_node, 0, R.equacao2_to_number()));
                    currentNode.node_direito = null;
                    currentNode.node_esquerdo = null;
                }
            }
            Resolver(currentNode.node_direito);
            Resolver(currentNode.node_esquerdo);
        }
    }

    public String toString(tree currentNode, int sup){
        if (currentNode == null) return "";
        else if (currentNode.folha()) return currentNode.this_node;
        else{
            if (currentNode.this_node.contains("()")) return toString(currentNode.node_direito, sup) + currentNode.this_node.substring(0, currentNode.this_node.length() - 1) + toString(currentNode.node_esquerdo, sup) + ")";
            else return toString(currentNode.node_esquerdo, sup) + currentNode.this_node + toString(currentNode.node_direito, sup);
        }
    }

    public int CountNodes(tree currentNode)  {
        return (currentNode == null) ? 0 : 1 + CountNodes(currentNode.node_direito) + CountNodes(currentNode.node_esquerdo);
    }

    public void PlaceNumber(tree currentNode){
        if (currentNode != null){
            if (currentNode.this_node.equals("e")) currentNode.this_node = "2.718281828459045";
            PlaceNumber(currentNode.node_direito);
            PlaceNumber(currentNode.node_esquerdo);
        }
    }



}
