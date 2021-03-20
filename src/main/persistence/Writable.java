package persistence;

import org.json.JSONObject;
// Represents a JSONObject that is writable.

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
