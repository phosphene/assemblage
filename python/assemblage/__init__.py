"""Package initialization for assemblage python engine."""
from .canon import canonical_json_bytes, canonical_json_str, sha256_hex

__all__ = ["canonical_json_bytes", "canonical_json_str", "sha256_hex"]

try:
    from .graph import AssemblageGraph
    __all__.append("AssemblageGraph")
except ImportError:
    pass
