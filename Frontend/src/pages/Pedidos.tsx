import { motion } from "framer-motion";
import { ShoppingCart, Package } from "lucide-react";
import { Button } from "@/components/ui/button";
import { useCart} from "@/context/CartContext";
import {crearventa} from "@/services/ventaService";
import { crearDetalleVenta } from "@/services/detalleVentaService";

export default function Pedidos() {
  const { items, totalPrice, removeFromCart, updateQuantity, clearCart } = useCart();
  const checkout = async () => {
  try {
    const venta = await crearventa({
      total: totalPrice,
      estado: true,
      usuario: {id: 1}
      });
    
      for (const item of items) {
        await crearDetalleVenta({
          cantidad: item.quantity,
          precio: item.product.price,
          venta: venta,
          producto: item.product
        });
      }

      clearCart();
      alert("Pedido enviado con exito");
    } catch (error) {
      alert("Error al enviar pedido");
    }
  };
  return (
    <div className="py-16">
      <div className="container max-w-2xl">
        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} className="text-center mb-12">
          <p className="text-primary text-sm tracking-[0.3em] font-semibold uppercase mb-3">Tu Carrito</p>
          <h1 className="font-display text-4xl font-bold text-foreground">Pedidos</h1>
        </motion.div>

        {items.length === 0 ? (

          <div className="bg-card border border-border rounded-lg p-12 text-center">
            <ShoppingCart className="h-16 w-16 text-muted-foreground mx-auto mb-4" />
            <h3 className="text-foreground font-display text-xl font-bold mb-2">
              Tu carrito está vacío
            </h3>
            <Button asChild>
              <a href="/catalogo">Ver Catálogo</a>
            </Button>
          </div>

          ) : (

          <div className="space-y-4">

          {items.map((item) => (

          <div
          key={`${item.product.id}-${item.size}-${item.color}`}
          className="flex justify-between items-center bg-card border border-border rounded-lg p-4"
          >

          <div>
          <h3 className="font-semibold">{item.product.name}</h3>
          <p className="text-sm text-muted-foreground">
          Talla: {item.size} · Color: {item.color}
          </p>
          <p className="text-primary font-bold">
          ${item.product.price.toLocaleString("es-CO")}
          </p>
          </div>

          <div className="flex items-center gap-3">

              <button
                  onClick={() =>
                  updateQuantity(item.product.id,item.size,item.color,item.quantity - 1)}>
                  -
              </button>

              <span>{item.quantity}</span>

              <button onClick={() => updateQuantity(item.product.id,item.size,item.color,item.quantity + 1)}>
                +
              </button>

              <button onClick={() => removeFromCart(item.product.id, item.size, item.color)}>
                Eliminar
              </button>

          </div>

          </div>

          ))}

          </div>

          )}

          {items.length > 0 && (

          <div className="mt-8 bg-card border border-border rounded-lg p-6">

          <h2 className="text-lg font-bold mb-4">
            Total: ${totalPrice.toLocaleString("es-CO")}
          </h2>

          <Button size="lg" onClick={checkout}>
            Comprar
          </Button>

          </div>

          )}
      </div>
    </div>
  );
}
