package com.sailor.inventario.main;

import java.util.Scanner;
import com.sailor.inventario.model.Producto;
import com.sailor.inventario.model.Proveedor;
import com.sailor.inventario.model.Usuario;
import com.sailor.inventario.model.Inventario;

public class sistema {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Usuario usuario = new Usuario();

        usuario.registrarUsuario();

        System.out.println("Usuario registrado correctamente");

        usuario.mostrarUsuario(1); // ahora sí funciona

        sc.close();
    }

}
