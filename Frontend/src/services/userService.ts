import { getAuthHeaders } from "@/lib/utils";

const API = "http://localhost:8081/api/usuarios";

export type LoginResponse = {
  token: string;
  usuario: Usuario;
};

export type Usuario = {
  id?: number;
  nombre: string;
  apellido: string;
  correo: string;
  password: string;
  rol: string;
  direccion: string;
};

export type PaginatedUsuarios = {
  content: Usuario[]
  totalPages: number
  totalElements: number
  number: number
  size: number
  first: boolean
  last: boolean
}

// Funciones auxiliares para token
const setToken = (token: string): void => {
  localStorage.setItem("authToken", token);
};

const removeToken = (): void => {
  localStorage.removeItem("authToken");
};

export const loginUsuario = async (correo: string, password: string): Promise<LoginResponse> => {
  const res = await fetch(`${API}/login`, {
    method: "POST",
    headers: getAuthHeaders(),
    body: JSON.stringify({ correo, password }),
  });

  if (!res.ok) {
    throw new Error("Credenciales incorrectas");
  }

  const data: LoginResponse = await res.json();

  // Guardar token y usuario
  setToken(data.token);
  localStorage.setItem("usuario", JSON.stringify(data.usuario));

  return data;
};

export const getUsuarioSesion = async () => {
  const data = localStorage.getItem("usuario");
  return data ? JSON.parse(data) : null;
};

export const logoutUsuario = async () => {
  removeToken();
  localStorage.removeItem("usuario");
};

export const registrarUsuario = async (usuario: Usuario) => {
  const res = await fetch(`${API}/registrar`, {
    method: "POST",
    headers: getAuthHeaders(),
    body: JSON.stringify(usuario),
  });

  return res.json();
};

export const getUsuarios = async () => {
  const res = await fetch(API, {
    headers: getAuthHeaders(),
  });
  return res.json();
};

// Obtener usuarios con paginación
export const getUsuariosPaginados = async (page: number = 0, size: number = 10): Promise<PaginatedUsuarios> => {
  const res = await fetch(`${API}/paginado?page=${page}&size=${size}`, {
    headers: getAuthHeaders(),
  });
  if (!res.ok) throw new Error("Error al obtener usuarios");
  return res.json();
};

export const getUsuario = async (id: number) => {
  const res = await fetch(`${API}/buscar/${id}`, {
    headers: getAuthHeaders(),
  });
  return res.json();
};

export const actualizarUsuario = async (id: number, usuario: Usuario) => {
  const res = await fetch(`${API}/${id}`, {
    method: "PUT",
    headers: getAuthHeaders(),
    body: JSON.stringify(usuario),
  });

  return res.json();
};

export const eliminarUsuario = async (id: number) => {
  const res = await fetch(`${API}/${id}`, {
    method: "DELETE",
    headers: getAuthHeaders(),
  });

  if (!res.ok) {
    throw new Error("Error al eliminar usuario");
  }
};