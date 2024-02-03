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

    public void processKeyPressEvent(UUID userId, long timestamp) {
        lastKeyPressTimestamps.put(userId, timestamp);
    }

    public Set<UUID> checkInactiveUsers() {
        Set<UUID> inactiveUsers = new HashSet<>();
        long currentTime = System.currentTimeMillis();

        for (UUID userId : lastKeyPressTimestamps.keySet()) {
            long lastKeyPressTime = lastKeyPressTimestamps.get(userId);

            if (currentTime - lastKeyPressTime > GEN_INSIGHT_INTERVAL) {
                inactiveUsers.add(userId);
            }
        }
        return inactiveUsers;
    }
}
