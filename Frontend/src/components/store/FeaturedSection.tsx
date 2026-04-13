import { motion } from "framer-motion";
import { Link } from "react-router-dom";

const categories = [
  { name: "Medias hasta la Cadera", count: 32 },
  { name: "Pantimedias", count: 24 },
  { name: "Medias de Compresión", count: 16 },
  { name: "Medias con Diseño", count: 20 },
];

export default function FeaturedSection() {
  return (
    <section className="py-20 bg-background">
      <div className="container">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: true }}
          className="text-center mb-16"
        >
          <p className="text-primary text-sm tracking-[0.3em] font-semibold uppercase mb-3">Categorías</p>
          <h2 className="font-display text-3xl md:text-4xl font-bold text-foreground">Explora nuestro catálogo</h2>
        </motion.div>

        <div className="grid grid-cols-2 md:grid-cols-4 gap-4 mb-20">
          {categories.map((cat, i) => (
            <motion.div
              key={cat.name}
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              viewport={{ once: true }}
              transition={{ delay: i * 0.1 }}
            >
              <Link
                to="/catalogo"
                className="block bg-card border border-border rounded-lg p-6 text-center hover:border-primary/50 transition-all hover:shadow-glow group"
              >
                <h3 className="font-display text-lg font-bold text-foreground group-hover:text-primary transition-colors">{cat.name}</h3>
                <p className="text-muted-foreground text-sm mt-1">{cat.count} productos</p>
              </Link>
            </motion.div>
          ))}
        </div>

        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: true }}
          className="relative rounded-xl overflow-hidden bg-card border border-border"
        >
          <div className="py-20 flex items-center justify-center flex-col gap-4">
            <p className="text-primary text-sm tracking-[0.3em] font-semibold uppercase">Nueva Temporada 2026</p>
            <h2 className="font-display text-3xl md:text-5xl font-bold text-foreground">Colección Primavera</h2>
            <p className="text-muted-foreground max-w-lg text-center">Medias de todos los colores y estilos para cada ocasión</p>
            <Link to="/colecciones">
              <button className="mt-2 border border-primary text-primary px-8 py-3 text-xs tracking-widest font-semibold uppercase hover:bg-primary hover:text-primary-foreground transition-all">
                VER COLECCIÓN
              </button>
            </Link>
          </div>
        </motion.div>
      </div>
    </section>
  );
}
