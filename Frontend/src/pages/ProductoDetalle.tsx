import { useParams, Link } from "react-router-dom";
import { useState, useEffect } from "react";
import { motion } from "framer-motion";
import { useCart } from "@/context/CartContext";
import { Button } from "@/components/ui/button";
import { getProductoById, ProductoFrontend } from "@/services/productService";
import {
  ShoppingCart, Star, Truck, Shield, RotateCcw, ChevronRight,
  Minus, Plus, Heart, Share2, Check
} from "lucide-react";

export default function ProductoDetalle() {
  const { id } = useParams();
  const [product, setProduct] = useState<ProductoFrontend | null>(null);

  useEffect(() => {
    if (id) {
    getProductoById(Number(id)).then(setProduct);
    }
  }, [id]);

  const { addToCart, setIsCartOpen } = useCart();
  const [selectedSize, setSelectedSize] = useState("");
  const [selectedColor, setSelectedColor] = useState("");
  const [quantity, setQuantity] = useState(1);
  const [activeTab, setActiveTab] = useState<"desc" | "details" | "reviews">("desc");
  
  if (!product) {
    return (
      <div className="py-20 text-center">
        <p className="text-muted-foreground text-lg">Cargando producto... </p>
      </div>
    );
  }

  const recommended : ProductoFrontend[] = [];
  const handleAddToCart = () => {
    if (!selectedSize || !selectedColor || !product) return;
    addToCart(
      {
        id: product.id,
        name: product.name,
        description: product.description,
        price: product.price,
        emoji: product.emoji,
        originalPrice: product.originalPrice,
        stock: product.stock,
        category: product.category,
        rating: product.rating,
        reviews: product.reviews,
        sku: product.sku,
        tags: product.tags,
        colors: product.colors.map((c) => ({ name: c.name, value: c.value, emoji: "" })),
        sizes: product.sizes,
        details: product.details,
      },
      selectedSize,
      selectedColor,
      quantity
    );
    setIsCartOpen(true);
  };

  const discount = product.originalPrice
    ? Math.round(((product.originalPrice - product.price) / product.originalPrice) * 100)
    : 0;

  return (

    <div className="py-8">
      <div className="container">
        {/* Breadcrumb */}
        <nav className="flex items-center gap-2 text-xs text-muted-foreground mb-6">
          <Link to="/" className="hover:text-foreground transition-colors">Inicio</Link>
          <ChevronRight className="h-3 w-3" />
          <Link to="/catalogo" className="hover:text-foreground transition-colors">Catálogo</Link>
          <ChevronRight className="h-3 w-3" />
          <Link to={`/catalogo?cat=${encodeURIComponent(product.category)}`} className="hover:text-foreground transition-colors">{product.category}</Link>
          <ChevronRight className="h-3 w-3" />
          <span className="text-foreground">{product.name}</span>
        </nav>

        <div className="grid md:grid-cols-2 gap-8 lg:gap-12">
          {/* Product Image */}
          <motion.div initial={{ opacity: 0, x: -20 }} animate={{ opacity: 1, x: 0 }}>
            <div className="bg-secondary rounded-2xl border border-border aspect-square flex items-center justify-center relative overflow-hidden">
              <span className="text-[120px] md:text-[160px]">{product.emoji}</span>
              {discount > 0 && (
                <span className="absolute top-4 left-4 bg-primary text-primary-foreground text-xs font-bold px-3 py-1 rounded-full">
                  -{discount}%
                </span>
              )}
              {product.tags.includes("Bestseller") || product.tags.includes("Más vendida") ? (
                <span className="absolute top-4 right-4 bg-accent text-accent-foreground text-xs font-bold px-3 py-1 rounded-full">
                  🔥 Bestseller
                </span>
              ) : null}
              <div className="absolute bottom-4 right-4 flex gap-2">
                <button className="w-10 h-10 rounded-full bg-card/80 backdrop-blur border border-border flex items-center justify-center text-muted-foreground hover:text-primary transition-colors">
                  <Heart className="h-5 w-5" />
                </button>
                <button className="w-10 h-10 rounded-full bg-card/80 backdrop-blur border border-border flex items-center justify-center text-muted-foreground hover:text-primary transition-colors">
                  <Share2 className="h-5 w-5" />
                </button>
              </div>
            </div>
          </motion.div>

          {/* Product Info */}
          <motion.div initial={{ opacity: 0, x: 20 }} animate={{ opacity: 1, x: 0 }} className="space-y-5">
            {/* Tags */}
            <div className="flex flex-wrap gap-2">
              {product.tags?.map((tag) => (
                <span key={tag} className="text-[10px] uppercase tracking-wider font-semibold bg-secondary text-muted-foreground px-2.5 py-1 rounded-full border border-border">
                  {tag}
                </span>
              ))}
            </div>

            <h1 className="font-display text-2xl md:text-3xl font-bold text-foreground">{product.name}</h1>

            {/* Rating */}
            <div className="flex items-center gap-3">
              <div className="flex items-center gap-1">
                {[...Array(5)].map((_, i) => (
                  <Star key={i} className={`h-4 w-4 ${i < Math.floor(product.rating) ? "text-accent fill-accent" : "text-muted-foreground/30"}`} />
                ))}
              </div>
              <span className="text-sm font-medium text-foreground">{product.rating}</span>
              <span className="text-sm text-muted-foreground">({product.reviews} reseñas)</span>
              <span className="text-xs text-muted-foreground">·</span>
              <span className={`text-xs font-medium ${product.stock > 20 ? "text-green-400" : "text-accent"}`}>
                {product.stock > 20 ? "En stock" : `¡Solo quedan ${product.stock}!`}
              </span>
            </div>

            {/* Price */}
            <div className="flex items-baseline gap-3">
              <span className="text-3xl font-bold text-primary">${product.price.toLocaleString("es-CO")}</span>
              {product.originalPrice && (
                <span className="text-lg text-muted-foreground line-through">${product.originalPrice.toLocaleString("es-CO")}</span>
              )}
            </div>

            <p className="text-muted-foreground text-sm leading-relaxed">{product.description}</p>

            {/* Color selector */}
            <div>
              <p className="text-sm font-medium text-foreground mb-2">
                Color: <span className="text-muted-foreground font-normal">{selectedColor || "Selecciona"}</span>
              </p>
              <div className="flex gap-3">
                {product.colors?.map((c) => (
                  <button
                    key={c.name}
                    onClick={() => setSelectedColor(c.name)}
                    className={`relative w-10 h-10 rounded-full border-2 transition-all ${
                      selectedColor === c.name ? "border-primary scale-110" : "border-border hover:border-muted-foreground"
                    }`}
                    style={{ backgroundColor: c.value }}
                    title={c.name}
                  >
                    {selectedColor === c.name && (
                      <Check className="absolute inset-0 m-auto h-4 w-4 text-white drop-shadow-lg" />
                    )}
                  </button>
                ))}
              </div>
            </div>

            {/* Size selector */}
            <div>
              <div className="flex items-center justify-between mb-2">
                <p className="text-sm font-medium text-foreground">
                  Talla: <span className="text-muted-foreground font-normal">{selectedSize || "Selecciona"}</span>
                </p>
                <button className="text-xs text-primary hover:underline">Guía de tallas</button>
              </div>
              <div className="flex gap-2">
                {product.sizes?.map((s) => (
                  <button
                    key={s}
                    onClick={() => setSelectedSize(s)}
                    className={`w-12 h-12 rounded-lg border-2 text-sm font-semibold transition-all ${
                      selectedSize === s
                        ? "border-primary bg-primary/10 text-primary"
                        : "border-border text-muted-foreground hover:border-muted-foreground/50 hover:text-foreground"
                    }`}
                  >
                    {s}
                  </button>
                ))}
              </div>
            </div>

            {/* Quantity + Add to cart */}
            <div className="flex items-center gap-3">
              <div className="flex items-center border-2 border-border rounded-lg">
                <button
                  onClick={() => setQuantity(Math.max(1, quantity - 1))}
                  className="w-10 h-10 flex items-center justify-center text-muted-foreground hover:text-foreground transition-colors"
                >
                  <Minus className="h-4 w-4" />
                </button>
                <span className="w-10 text-center font-semibold text-foreground">{quantity}</span>
                <button
                  onClick={() => setQuantity(quantity + 1)}
                  className="w-10 h-10 flex items-center justify-center text-muted-foreground hover:text-foreground transition-colors"
                >
                  <Plus className="h-4 w-4" />
                </button>
              </div>

              <Button
                variant="heroFilled"
                size="lg"
                className="flex-1 gap-2"
                onClick={handleAddToCart}
                disabled={!selectedSize || !selectedColor}
              >
                <ShoppingCart className="h-5 w-5" />
                {!selectedSize || !selectedColor ? "Selecciona opciones" : "Agregar al carrito"}
              </Button>
            </div>

            {/* Trust badges */}
            <div className="grid grid-cols-3 gap-3 pt-2">
              <div className="flex flex-col items-center text-center gap-1.5 p-3 bg-secondary/50 rounded-lg border border-border">
                <Truck className="h-5 w-5 text-primary" />
                <span className="text-[10px] text-muted-foreground leading-tight">Envío gratis a todo el país</span>
              </div>
              <div className="flex flex-col items-center text-center gap-1.5 p-3 bg-secondary/50 rounded-lg border border-border">
                <Shield className="h-5 w-5 text-primary" />
                <span className="text-[10px] text-muted-foreground leading-tight">Compra 100% segura</span>
              </div>
              <div className="flex flex-col items-center text-center gap-1.5 p-3 bg-secondary/50 rounded-lg border border-border">
                <RotateCcw className="h-5 w-5 text-primary" />
                <span className="text-[10px] text-muted-foreground leading-tight">Cambios en 30 días</span>
              </div>
            </div>

            {/* SKU */}
            <p className="text-xs text-muted-foreground">SKU: {product.sku}</p>
          </motion.div>
        </div>

        {/* Tabs: Description / Details / Reviews */}
        <div className="mt-12 border-t border-border pt-8">
          <div className="flex gap-6 mb-6 border-b border-border">
            {([["desc", "Descripción"], ["details", "Especificaciones"], ["reviews", "Reseñas"]] as const).map(([key, label]) => (
              <button
                key={key}
                onClick={() => setActiveTab(key)}
                className={`pb-3 text-sm font-medium transition-colors border-b-2 -mb-px ${
                  activeTab === key ? "border-primary text-primary" : "border-transparent text-muted-foreground hover:text-foreground"
                }`}
              >
                {label}
              </button>
            ))}
          </div>

          {activeTab === "desc" && (
            <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="max-w-2xl">
              <p className="text-muted-foreground leading-relaxed">{product.description}</p>
            </motion.div>
          )}
          {activeTab === "details" && (
            <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="max-w-2xl">
              <ul className="space-y-2">
                {product.details?.map((d, i) => (
                  <li key={i} className="flex items-start gap-2 text-sm text-muted-foreground">
                    <Check className="h-4 w-4 text-primary shrink-0 mt-0.5" />
                    {d}
                  </li>
                ))}
              </ul>
            </motion.div>
          )}
          {activeTab === "reviews" && (
            <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="max-w-2xl">
              <div className="flex items-center gap-4 mb-6">
                <div className="text-center">
                  <p className="text-4xl font-bold text-foreground">{product.rating}</p>
                  <div className="flex items-center gap-0.5 mt-1">
                    {[...Array(5)].map((_, i) => (
                      <Star key={i} className={`h-4 w-4 ${i < Math.floor(product.rating) ? "text-accent fill-accent" : "text-muted-foreground/30"}`} />
                    ))}
                  </div>
                  <p className="text-xs text-muted-foreground mt-1">{product.reviews} reseñas</p>
                </div>
                <div className="flex-1 space-y-1">
                  {[5, 4, 3, 2, 1].map((star) => {
                    const pct = star === 5 ? 68 : star === 4 ? 22 : star === 3 ? 7 : star === 2 ? 2 : 1;
                    return (
                      <div key={star} className="flex items-center gap-2 text-xs">
                        <span className="text-muted-foreground w-3">{star}</span>
                        <Star className="h-3 w-3 text-accent fill-accent" />
                        <div className="flex-1 h-2 bg-secondary rounded-full overflow-hidden">
                          <div className="h-full bg-accent rounded-full" style={{ width: `${pct}%` }} />
                        </div>
                        <span className="text-muted-foreground w-8 text-right">{pct}%</span>
                      </div>
                    );
                  })}
                </div>
              </div>
              <p className="text-sm text-muted-foreground italic">Las reseñas estarán disponibles próximamente.</p>
            </motion.div>
          )}
        </div>

        {/* Recommendations */}
        {recommended.length > 0 && (
          <div className="mt-12 border-t border-border pt-8">
            <h2 className="font-display text-xl font-bold text-foreground mb-6">También te puede gustar</h2>
            <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
              {recommended.map((p) => (
                <Link
                  key={p.id}
                  to={`/producto/${p.id}`}
                  className="bg-card border border-border rounded-lg overflow-hidden group hover:border-primary/50 transition-all"
                >
                  <div className="h-36 bg-secondary flex items-center justify-center text-5xl group-hover:scale-105 transition-transform">
                    {p.emoji}
                  </div>
                  <div className="p-3">
                    <h3 className="font-medium text-xs text-foreground truncate">{p.name}</h3>
                    <p className="text-primary font-bold text-sm mt-1">${p.price.toLocaleString("es-CO")}</p>
                  </div>
                </Link>
              ))}
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
