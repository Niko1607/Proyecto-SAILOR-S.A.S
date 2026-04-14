import { Sheet, SheetContent, SheetHeader, SheetTitle } from "@/components/ui/sheet";
import { Button } from "@/components/ui/button";
import { useCart } from "@/context/CartContext";
import { Minus, Plus, Trash2, ShoppingBag } from "lucide-react";
import { Link } from "react-router-dom";

export default function CartDrawer() {
  const { items, isCartOpen, setIsCartOpen, removeFromCart, updateQuantity, totalItems, totalPrice } = useCart();

  return (
    <Sheet open={isCartOpen} onOpenChange={setIsCartOpen}>
      <SheetContent className="bg-card border-border w-full sm:max-w-lg flex flex-col">
        <SheetHeader>
          <SheetTitle className="text-foreground font-display flex items-center gap-2">
            <ShoppingBag className="h-5 w-5 text-primary" />
            Carrito ({totalItems})
          </SheetTitle>
        </SheetHeader>

        {items.length === 0 ? (
          <div className="flex-1 flex flex-col items-center justify-center text-center gap-4">
            <ShoppingBag className="h-16 w-16 text-muted-foreground/30" />
            <p className="text-muted-foreground">Tu carrito está vacío</p>
            <Button variant="outline" onClick={() => setIsCartOpen(false)} asChild>
              <Link to="/catalogo">Ver catálogo</Link>
            </Button>
          </div>
        ) : (
          <>
            <div className="flex-1 overflow-y-auto space-y-4 py-4">
              {items.map((item) => (
                <div
                  key={`${item.product.id}-${item.size}-${item.color}`}
                  className="flex gap-3 bg-secondary/50 rounded-lg p-3 border border-border"
                >
                  <div className="w-16 h-16 rounded-md bg-secondary flex items-center justify-center text-3xl shrink-0">
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
                        className="w-6 h-6 rounded bg-secondary border border-border flex items-center justify-center text-muted-foreground hover:text-foreground transition-colors"
                      >
                        <Minus className="h-3 w-3" />
                      </button>
                      <span className="text-sm font-medium text-foreground w-6 text-center">{item.quantity}</span>
                      <button
                        onClick={() => updateQuantity(item.product.id, item.size, item.color, item.quantity + 1)}
                        className="w-6 h-6 rounded bg-secondary border border-border flex items-center justify-center text-muted-foreground hover:text-foreground transition-colors"
                      >
                        <Plus className="h-3 w-3" />
                      </button>
                      <button
                        onClick={() => removeFromCart(item.product.id, item.size, item.color)}
                        className="ml-auto text-muted-foreground hover:text-destructive transition-colors"
                      >
                        <Trash2 className="h-4 w-4" />
                      </button>
                    </div>
                  </div>
                </div>
              ))}
            </div>

            <div className="border-t border-border pt-4 space-y-3">
              <div className="flex justify-between text-sm">
                <span className="text-muted-foreground">Subtotal ({totalItems} productos)</span>
                <span className="font-bold text-foreground">${totalPrice.toLocaleString("es-CO")}</span>
              </div>
              <div className="flex justify-between text-sm">
                <span className="text-muted-foreground">Envío</span>
                <span className="text-accent font-medium">Gratis</span>
              </div>
              <div className="flex justify-between text-base font-bold border-t border-border pt-3">
                <span className="text-foreground">Total</span>
                <span className="text-primary">${totalPrice.toLocaleString("es-CO")}</span>
              </div>
              <Button variant="heroFilled" size="lg" className="w-full" onClick={() => setIsCartOpen(false)} asChild>
                <Link to="/pedidos">Finalizar compra</Link>
              </Button>
            </div>
          </>
        )}
      </SheetContent>
    </Sheet>
  );
}
