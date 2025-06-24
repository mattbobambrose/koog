package ai.koog.agents.snapshot.providers

import ai.koog.agents.snapshot.feature.AgentCheckpointData
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * In-memory implementation of [AgentCheckpointStorageProvider].
 * This provider stores snapshots in a mutable map.
 */
public class InMemoryAgentCheckpointStorageProvider : AgentCheckpointStorageProvider {
    private val mutex = Mutex()
    private val snapshots = mutableMapOf<String, AgentCheckpointData>()

    override suspend fun getCheckpoint(checkpointId: String): AgentCheckpointData? {
        return mutex.withLock {
            snapshots[checkpointId]
        }
    }

    override suspend fun saveCheckpoint(checkpointId: String, agentCheckpointData: AgentCheckpointData) {
        mutex.withLock {
            snapshots[checkpointId] = agentCheckpointData
        }
    }
}