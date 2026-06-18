// ─────────────────────────────────────────────────────────────────────
//  EjectedMultiverse.java
//  Główna klasa moda (NeoForge 1.21.1, Java 21)
//
//  Faza 1: smoke test — klasa się ładuje, logger działa, common setup hook
//          się rejestruje. Nic więcej tu nie implementujemy — to fundament.
//
//  Modid:        ejectedmultiverse
//  Display name: (Ejected) Multiverse Core v.6.6.6
//  Licencja:     MIT (kod), CC0/CC-BY (asset'y z atrybucją)
// ─────────────────────────────────────────────────────────────────────

package com.dontletmeuseopencode.ejectedmultiverse;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

/**
 * Główny entrypoint moda. NeoForge wymaga jednej klasy oznaczonej {@link Mod}.
 * Tutaj trzymamy tylko rejestracje globalne — konkretne systemy (wymiary,
 * bloki, itemy, encje) lądują w podpakietach (dimension/, block/, item/, …).
 */
@Mod(EjectedMultiverse.MOD_ID)
public class EjectedMultiverse {

    /** Musi być zsynchronizowany z {@code mod_id} w gradle.properties i mods.toml. */
    public static final String MOD_ID = "ejectedmultiverse";

    /** Wyświetlana nazwa (używana w bannerach, GUI, logach). */
    public static final String MOD_NAME = "(Ejected) Multiverse Core v.6.6.6";

    private static final Logger LOGGER = LogUtils.getLogger();

    public EjectedMultiverse(IEventBus modEventBus) {
        LOGGER.info("[{}] Booting {} — Phase 1 skeleton", MOD_ID, MOD_NAME);

        // Rejestrujemy się na globalny event bus tego moda.
        // W kolejnych fazach tu wpadną DeferredRegister<>, attachCapabilities itp.
        modEventBus.register(this);
    }

    /**
     * Common setup — odpala się po obu stronach (client + dedicated server).
     * Faza 1: tylko log. Faza 2+: rejestracja wymiarów, pakietów sieciowych.
     */
    @SubscribeEvent
    public void onCommonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("[{}] Common setup OK", MOD_ID);
    }
}
