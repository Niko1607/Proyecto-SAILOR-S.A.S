const API = "http://localhost:8081/api/usuarios";

export type Usuario = {
  id?: number;
  nombre: string;
  apellido: string;
  correo: string;
  password: string;
  rol: string;
  direccion: string;
};

export const loginUsuario = async (correo: string, password: string) => {
  const res = await fetch(`${API}/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ correo, password }),
  });

  if (!res.ok) {
    throw new Error("Credenciales incorrectas");
  }

  const data = await res.json();

  localStorage.setItem("usuario", JSON.stringify(data));

  return data;
};

export const getUsuarioSesion = async () => {
  const data = localStorage.getItem("usaurio");
  return data ? JSON.parse(data) : null;
};

export const logoutUsuario = async () => {
  localStorage.removeItem("usuario");
};

export const registrarUsuario = async (usuario: Usuario) => {
  const res = await fetch(`${API}/registrar`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(usuario),
  });

  return res.json();
};

export const getUsuarios = async () => {
  const res = await fetch(API);
  return res.json();
};

export const getUsuario = async (id: number) => {
  const res = await fetch(`${API}/buscar/${id}`);
  return res.json();
};

export const actualizarUsuario = async (id: number, usuario: Usuario) => {
  const res = await fetch(`${API}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(usuario),
  });

  return res.json();
};

export const eliminarUsuario = async (id: number) => {
  const res = await fetch(`${API}/${id}`, {
    method: "DELETE",
  });

  if (!res.ok) {
    throw new Error("Error al eliminar usuario");
  }
};