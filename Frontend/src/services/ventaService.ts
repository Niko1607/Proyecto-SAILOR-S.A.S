const API_URL = "http://localhost:8081/api/ventas";

export type Venta = {
  id?: number
  fecha?: string
  estado?: boolean
  total: number
  usuario?: {
    id: number
  }
}

export const getVentas = async () => {
  const response = await fetch(API_URL);

  if (!response.ok) {
    throw new Error("Error al obtener ventas");
  }

  return response.json();
};

export const getVentaById = async (id: number) => {
    const response = await fetch(`${API_URL}/${id}`);
    
    if(!response.ok) {
      throw new Error("Error al obtener venta");
    }

    return response.json();
};

export const crearventa = async (venta: Venta) => {
    const response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(venta),
    });

    return response.json();
};

export const eliminarVenta = async (id: number) => {
  await fetch(`${API_URL}/${id}`, {
    method: "DELETE",
  });
};

export const actualizarVenta = async (id: number, venta: Venta) => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(venta),
  });

  return response.json();
};

