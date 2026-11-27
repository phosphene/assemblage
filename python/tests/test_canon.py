import pytest
import json
from pathlib import Path
from assemblage.canon import canonical_json_bytes, canonical_json_str, sha256_hex

TEST_RESOURCES = Path(__file__).resolve().parent.parent.parent / "test" / "resources" / "rfc8785"

RFC8785_FILES = ["arrays", "french", "structures", "unicode", "values", "weird"]

@pytest.mark.parametrize("name", RFC8785_FILES)
def test_rfc8785_official_vectors(name):
    in_file = TEST_RESOURCES / f"in_{name}.json"
    out_file = TEST_RESOURCES / f"out_{name}.json"
    
    expected_bytes = out_file.read_bytes()
    raw_in = in_file.read_text(encoding="utf-8")
    parsed_in = json.loads(raw_in)
    
    actual_bytes = canonical_json_bytes(parsed_in)
    assert actual_bytes == expected_bytes, f"Mismatch on {name}"
    
    # Check string decoding
    assert canonical_json_str(parsed_in) == expected_bytes.decode("utf-8")

def test_number_formatting():
    samples = [
        (0.0, "0"),
        (-0.0, "0"),
        (5e-324, "5e-324"),
        (-5e-324, "-5e-324"),
        (1.7976931348623157e+308, "1.7976931348623157e+308"),
        (-1.7976931348623157e+308, "-1.7976931348623157e+308"),
        (9007199254740992.0, "9007199254740992"),
        (-9007199254740992.0, "-9007199254740992"),
        (1e23, "1e+23"),
        (1e21, "1e+21"),
        (0.000001, "0.000001"),
        (333333333.3333333, "333333333.3333333"),
        (-0.0000033333333333333333, "-0.0000033333333333333333"),
    ]
    for num, expected in samples:
        assert canonical_json_str(num) == expected
