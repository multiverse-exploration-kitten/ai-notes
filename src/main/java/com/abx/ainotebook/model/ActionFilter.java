package com.abx.ainotebook.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ActionFilter {
    private static final long GEN_INSIGHT_INTERVAL = 10000;
    private Map<UUID, Long> lastKeyPressTimestamps;

    public ActionFilter() {
        this.lastKeyPressTimestamps = new HashMap<>();
    }

    public void processKeyPressEvent(UUID noteId, long timestamp) {
        lastKeyPressTimestamps.put(noteId, timestamp);
    }

    public Set<UUID> checkInactiveNotes() {
        Set<UUID> inactiveUsers = new HashSet<>();
        long currentTime = System.currentTimeMillis();

        for (UUID noteId : lastKeyPressTimestamps.keySet()) {
            long lastKeyPressTime = lastKeyPressTimestamps.get(noteId);

            if (currentTime - lastKeyPressTime > GEN_INSIGHT_INTERVAL) {
                inactiveUsers.add(noteId);
            }
        }
        return inactiveUsers;
    }
}