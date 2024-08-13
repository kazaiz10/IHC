package com.example.calculadoragrafica.MathEngine;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class calc {

    private tree root;
    private DecimalFormat df;

    public calc(tree root){
        this.root = root;
        if (!this.root.this_node.contains("to")) this.Expand();
        this.PlaceNumber();
        df = new DecimalFormat("############.#########");
    }

    public void setDecimarFormat(String Format)  {  this.df = new DecimalFormat(Format);  }

    public void Expand()    {  this.root.add(this.root);  }
    //Alterar constantes matemáticas para os seus valores numéricos. Tipo e = 2.718281828459045
    private void PlaceNumber() {  this.root.PlaceNumber(root);  }

    public void PrintTree(tree node){
        if (node != null){
            System.out.println(node.this_node + "/");
            PrintTree(node.node_direito);
            PrintTree(node.node_esquerdo);
        }
    }

    public void Resolver()  {  this.root.Resolver(this.root);  }
    public String toString()  {  return this.root.toString(this.root, 0);  }
    public int CountNodes()  {  return this.root.CountNodes(root);  }

    public String Result(){
        if (this.CountNodes() == 1) return this.toString();
        else{
            this.Resolver();
            return this.Result();
        }
    }

    public ArrayList<String> StepByStep(){
        ArrayList<String> Step = new ArrayList<>();
        while(this.CountNodes() > 1){
            Step.add(this.toString().replace("2.718281828459045", "e").replace("3.141592653589793", "pi"));
            this.Resolver();
        }
        Step.add((String) df.format(Double.parseDouble(this.toString())));
        return Step;
    }

}
