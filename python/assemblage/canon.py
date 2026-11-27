"""
Cross-Runtime Canonical JSON Serialization (RFC 8785 / JCS) (T-441).

Provides deterministic canonical JSON serialization and SHA-256 digests
compatible with JVM Clojure (phosphene.assemblage.canon) and the seL4 harness.
"""

import hashlib
from typing import Any, Union
from .canonicalize import canonicalize

def canonical_json_bytes(obj: Any) -> bytes:
    """
    Serializes a Python object to RFC 8785 canonical JSON UTF-8 bytes.
    """
    return canonicalize(obj)

def canonical_json_str(obj: Any) -> str:
    """
    Serializes a Python object to an RFC 8785 canonical JSON string.
    """
    return canonicalize(obj).decode("utf-8")

def sha256_hex(obj: Any) -> str:
    """
    Calculates the hex SHA-256 digest of the RFC 8785 canonical JSON representation of obj.
    """
    b = canonical_json_bytes(obj)
    return hashlib.sha256(b).hexdigest()
