import { motion } from "framer-motion";
import { Link } from "react-router-dom";
import { Button } from "@/components/ui/button";
import heroImage from "@/assets/hero-model.jpg";

export default function HeroSection() {
  return (
    <section className="bg-gradient-hero relative overflow-hidden min-h-[80vh] flex items-center">
      <div className="container grid grid-cols-1 lg:grid-cols-2 gap-8 items-center py-16">
        <motion.div
          initial={{ opacity: 0, y: 30 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.8 }}
          className="space-y-6"
        >
          <p className="text-primary text-sm tracking-[0.3em] font-semibold uppercase">
            Más de 21 años de elegancia
          </p>
          <h1 className="font-display text-5xl md:text-7xl font-bold leading-tight text-foreground">
            Medias que{" "}
            <em className="text-gradient-primary not-italic">transforman</em>
            <br />
            tu estilo
          </h1>
          <p className="text-muted-foreground max-w-md text-base leading-relaxed">
            Diseño, calidad y confort en cada fibra. Medias hasta la cadera que abrazan tu figura con la sofisticación que mereces.
          </p>
          <div className="flex gap-4 pt-2">
            <Link to="/catalogo">
              <Button variant="heroFilled" size="lg">Ver Colección</Button>
            </Link>
            <Link to="/nosotros">
              <Button variant="hero" size="lg">Conócenos</Button>
            </Link>
          </div>
        </motion.div>

        <motion.div
          initial={{ opacity: 0, x: 40 }}
          animate={{ opacity: 1, x: 0 }}
          transition={{ duration: 0.8, delay: 0.2 }}
          className="relative flex justify-center"
        >
          <div className="relative">
            <div className="absolute inset-0 border-2 border-accent/30 translate-x-4 translate-y-4" />
            <img
              src={heroImage}
              alt="Sailor - Medias elegantes para dama"
              className="relative z-10 w-full max-w-md object-cover shadow-card"
              width={800}
              height={1024}
            />
            <div className="absolute bottom-0 left-1/2 -translate-x-1/2 w-px h-16 bg-accent/50" />
          </div>
        </motion.div>
      </div>
    </section>
  );
}
