import { 
  Drawer,
  DrawerContent,
  DrawerHeader,
  DrawerTitle,
  DrawerFooter,
  DrawerClose
} from "@/components/ui/drawer";
import { Button } from "@/components/ui/button";
import { useCart } from "@/context/CartContext";
import { Minus, Plus, Trash2, ShoppingBag, X } from "lucide-react";
import { Link } from "react-router-dom";

export default function CartDrawer() {
  const { items, isCartOpen, setIsCartOpen, removeFromCart, updateQuantity, totalItems, totalPrice } = useCart();

  return (
    <Drawer open={isCartOpen} onOpenChange={setIsCartOpen}>
      <DrawerContent className="bg-card border-border h-[90vh] flex flex-col">
        <DrawerHeader className="border-b border-border">
          <div className="flex items-center justify-between w-full">
            <DrawerTitle className="text-foreground font-display flex items-center gap-2">
              <ShoppingBag className="h-5 w-5 text-primary" />
              Tu Carrito ({totalItems})
            </DrawerTitle>
            <DrawerClose asChild>
              <Button variant="ghost" size="icon" className="h-8 w-8">
                <X className="h-4 w-4" />
              </Button>
            </DrawerClose>
          </div>
        </DrawerHeader>

        {items.length === 0 ? (
          <div className="flex-1 flex flex-col items-center justify-center text-center gap-4 py-12">
            <ShoppingBag className="h-16 w-16 text-muted-foreground/30" />
            <div>
              <p className="text-muted-foreground text-lg font-medium mb-4">Tu carrito está vacío</p>
              <DrawerClose asChild>
                <Button variant="outline" asChild>
                  <Link to="/catalogo">Ver Catálogo</Link>
                </Button>
              </DrawerClose>
            </div>
          </div>
        ) : (
          <>
            <div className="flex-1 overflow-y-auto space-y-3 py-4 px-4">
              {items.map((item) => (
                <div
                  key={`${item.product.id}-${item.size}-${item.color}`}
                  className="flex gap-3 bg-secondary/50 rounded-lg p-3 border border-border hover:bg-secondary/60 transition-colors"
                >
                  <div className="w-16 h-16 rounded-md bg-secondary flex items-center justify-center text-3xl shrink-0 flex-shrink-0">
                    {item.product.emoji}
                  </div>
                  <div className="flex-1 min-w-0">
                    <h4 className="text-sm font-medium text-foreground truncate">{item.product.name}</h4>
                    <p className="text-xs text-muted-foreground mt-0.5">
                      Talla: {item.size} · Color: {item.color}
                    </p>
                    <p className="text-sm font-bold text-primary mt-1">
                      ${(item.product.price * item.quantity).toLocaleString("es-CO")}
                    </p>
                    <div className="flex items-center gap-2 mt-2">
                      <button
                        onClick={() => updateQuantity(item.product.id, item.size, item.color, item.quantity - 1)}
                        className="w-6 h-6 rounded bg-secondary border border-border flex items-center justify-center text-muted-foreground hover:text-foreground hover:bg-secondary/80 transition-colors"
                        title="Disminuir cantidad"
                      >
                        <Minus className="h-3 w-3" />
                      </button>
                      <span className="text-sm font-medium text-foreground w-6 text-center">{item.quantity}</span>
                      <button
                        onClick={() => updateQuantity(item.product.id, item.size, item.color, item.quantity + 1)}
                        className="w-6 h-6 rounded bg-secondary border border-border flex items-center justify-center text-muted-foreground hover:text-foreground hover:bg-secondary/80 transition-colors"
                        title="Aumentar cantidad"
                      >
                        <Plus className="h-3 w-3" />
                      </button>
                      <button
                        onClick={() => removeFromCart(item.product.id, item.size, item.color)}
                        className="ml-auto text-muted-foreground hover:text-destructive transition-colors hover:bg-destructive/10 w-6 h-6 flex items-center justify-center rounded"
                        title="Eliminar del carrito"
                      >
                        <Trash2 className="h-4 w-4" />
                      </button>
                    </div>
                  </div>
                </div>
              ))}
            </div>

            <DrawerFooter className="border-t border-border space-y-3">
              <div className="bg-secondary/50 rounded-lg p-3 space-y-2">
                <div className="flex justify-between text-sm">
                  <span className="text-muted-foreground">Subtotal</span>
                  <span className="font-bold text-foreground">${totalPrice.toLocaleString("es-CO")}</span>
                </div>
                <div className="flex justify-between text-sm">
                  <span className="text-muted-foreground">Envío</span>
                  <span className="text-accent font-medium">Gratis</span>
                </div>
                <div className="flex justify-between text-base font-bold border-t border-border pt-2">
                  <span className="text-foreground">Total</span>
                  <span className="text-primary text-lg">${totalPrice.toLocaleString("es-CO")}</span>
                </div>
              </div>
              <Button variant="heroFilled" size="lg" className="w-full" asChild>
                <DrawerClose asChild>
                  <Link to="/pedidos" onClick={() => setIsCartOpen(false)}>
                    Finalizar Compra
                  </Link>
                </DrawerClose>
              </Button>
              <Button variant="outline" size="lg" className="w-full" onClick={() => setIsCartOpen(false)}>
                Seguir Comprando
              </Button>
            </DrawerFooter>
          </>
        )}
      </DrawerContent>
    </Drawer>
  );
}
