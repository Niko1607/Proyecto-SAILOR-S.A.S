import { motion } from "framer-motion";
import { Tag, Clock } from "lucide-react";

const offers = [
  { name: "Hoodie Premium Negro", original: 189000, discounted: 139000, discount: "26%", endsIn: "3 días" },
  { name: "Jean Slim Fit Azul", original: 159000, discounted: 119000, discount: "25%", endsIn: "5 días" },
  { name: "Pack 3 Camisetas", original: 207000, discounted: 149000, discount: "28%", endsIn: "7 días" },
];

export default function Ofertas() {
  return (
    <div className="py-16">
      <div className="container">
        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} className="text-center mb-12">
          <p className="text-primary text-sm tracking-[0.3em] font-semibold uppercase mb-3">Descuentos</p>
          <h1 className="font-display text-4xl md:text-5xl font-bold text-foreground">Ofertas Especiales</h1>
        </motion.div>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          {offers.map((o, i) => (
            <motion.div
              key={o.name}
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              viewport={{ once: true }}
              transition={{ delay: i * 0.1 }}
              className="bg-card border border-border rounded-lg p-6 hover:border-primary/50 transition-all"
            >
              <div className="flex items-center gap-2 mb-4">
                <span className="bg-primary/10 text-primary text-xs font-bold px-3 py-1 rounded-full flex items-center gap-1">
                  <Tag className="h-3 w-3" /> -{o.discount}
                </span>
                <span className="text-muted-foreground text-xs flex items-center gap-1">
                  <Clock className="h-3 w-3" /> {o.endsIn}
                </span>
              </div>
              <div className="h-32 bg-secondary rounded-md flex items-center justify-center text-4xl mb-4">🛍️</div>
              <h3 className="font-medium text-foreground mb-2">{o.name}</h3>
              <div className="flex items-center gap-3">
                <span className="text-primary font-bold text-lg">${o.discounted.toLocaleString("es-CO")}</span>
                <span className="text-muted-foreground line-through text-sm">${o.original.toLocaleString("es-CO")}</span>
              </div>
            </motion.div>
          ))}
        </div>
      </div>
    </div>
  );
}
