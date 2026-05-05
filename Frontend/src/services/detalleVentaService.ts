const API_URL = "https://proyecto-sailor-sas-production.up.railway.app/api/detalleventas";

export type DetalleVenta = {
  id?: number
  cantidad: number
  precio: number
  venta: {
    id: number
  }
  producto: {
    id: number
  }
};

export const getDetallesPorVenta = async (ventaId: number) => {
  const response = await fetch(`${API_URL}/venta/${ventaId}`);

  if (!response.ok) {
    throw new Error("Error al obtener detalle de venta");
  }

  return await response.json();
};

export const getDetalleVentas = async () => {
  const response = await fetch(API_URL);

  if (!response.ok) {
    throw new Error("Error al obtener detalle ventas");
  }

  return response.json();
};

export const getDetalleVentaById = async (id: number) => {
  const response = await fetch(`${API_URL}/${id}`);
    
  if (!response.ok) {
    throw new Error("Error al obtener detalle venta");
  }

  return response.json();
};

export const crearDetalleVenta = async (detalleVenta: DetalleVenta) => {
  const response = await fetch(API_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(detalleVenta),
  });

  return response.json();
};

export const eliminarDetalleVenta = async (id: number) => {
  await fetch(`${API_URL}/${id}`, {
    method: "DELETE",
  });
};

export const actualizarDetalleVenta = async (id: number, detalleVenta: DetalleVenta) => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(detalleVenta),
  });

  return response.json();
};