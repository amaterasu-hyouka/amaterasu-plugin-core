package jp.amaterasu_hyouka.core.datapack;

public enum DataPackResourceType {
    ADVANCEMENT("advancement"),
    BANNER_PATTERN("banner_pattern"),
    CAT_VARIANT("cat_variant"),
    CHAT_TYPE("chat_type"),
    CHICKEN_VARIANT("chicken_variant"),
    COW_VARIANT("cow_variant"),
    DAMAGE_TYPE("damage_type"),
    DIALOG("dialog"),
    DIMENSION("dimension"),
    DIMENSION_TYPE("dimension_type"),
    ENCHANTMENT("enchantment"),
    ENCHANTMENT_PROVIDER("enchantment_provider"),
    FROG_VARIANT("frog_variant"),
    FUNCTION("function"),
    INSTRUMENT("instrument"),
    ITEM_MODIFIER("item_modifier"),
    JUKEBOX_SONG("jukebox_song"),
    LOOT_TABLE("loot_table"),
    PAINTING_VARIANT("painting_variant"),
    PIG_VARIANT("pig_variant"),
    PREDICATE("predicate"),
    RECIPE("recipe"),
    STRUCTURE("structure"),
    TEST_ENVIRONMENT("test_environment"),
    TEST_INSTANCE("test_instance"),
    TIMELINE("timeline"),
    TRIAL_SPAWNER("trial_spawner"),
    TRIM_MATERIAL("trim_material"),
    TRIM_PATTERN("trim_pattern"),
    WOLF_SOUND_VARIANT("wolf_sound_variant"),
    WOLF_VARIANT("wolf_variant"),
    ZOMBIE_NAUTILUS_VARIANT("zombie_nautilus_variant"),

    WORLDGEN_BIOME("worldgen/biome"),
    WORLDGEN_CONFIGURED_CARVER("worldgen/configured_carver"),
    WORLDGEN_CONFIGURED_FEATURE("worldgen/configured_feature"),
    WORLDGEN_DENSITY_FUNCTION("worldgen/density_function"),
    WORLDGEN_FLAT_LEVEL_GENERATOR_PRESET("worldgen/flat_level_generator_preset"),
    WORLDGEN_MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST("worldgen/multi_noise_biome_source_parameter_list"),
    WORLDGEN_NOISE("worldgen/noise"),
    WORLDGEN_NOISE_SETTINGS("worldgen/noise_settings"),
    WORLDGEN_PLACED_FEATURE("worldgen/placed_feature"),
    WORLDGEN_PROCESSOR_LIST("worldgen/processor_list"),
    WORLDGEN_STRUCTURE("worldgen/structure"),
    WORLDGEN_STRUCTURE_SET("worldgen/structure_set"),
    WORLDGEN_TEMPLATE_POOL("worldgen/template_pool"),
    WORLDGEN_WORLD_PRESET("worldgen/world_preset"),

    TAG_BANNER_PATTERN("tags/banner_pattern"),
    TAG_BLOCK("tags/block"),
    TAG_DAMAGE_TYPE("tags/damage_type"),
    TAG_DIALOG("tags/dialog"),
    TAG_ENCHANTMENT("tags/enchantment"),
    TAG_ENTITY_TYPE("tags/entity_type"),
    TAG_FLUID("tags/fluid"),
    TAG_FUNCTION("tags/function"),
    TAG_GAME_EVENT("tags/game_event"),
    TAG_INSTRUMENT("tags/instrument"),
    TAG_ITEM("tags/item"),
    TAG_PAINTING_VARIANT("tags/painting_variant"),
    TAG_POINT_OF_INTEREST_TYPE("tags/point_of_interest_type"),
    TAG_POTION("tags/potion"),
    TAG_TIMELINE("tags/timeline"),
    TAG_WORLDGEN_BIOME("tags/worldgen/biome"),
    TAG_WORLDGEN_CONFIGURED_FEATURE("tags/worldgen/configured_feature"),
    TAG_WORLDGEN_FLAT_LEVEL_GENERATOR_PRESET("tags/worldgen/flat_level_generator_preset"),
    TAG_WORLDGEN_STRUCTURE("tags/worldgen/structure"),
    TAG_WORLDGEN_WORLD_PRESET("tags/worldgen/world_preset");

    private final String directoryPath;

    DataPackResourceType(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }
}
