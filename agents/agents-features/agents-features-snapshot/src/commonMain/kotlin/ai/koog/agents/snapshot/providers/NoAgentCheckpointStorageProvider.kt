package ai.koog.agents.snapshot.providers

import ai.koog.agents.snapshot.feature.AgentCheckpointData
import io.github.oshai.kotlinlogging.KotlinLogging

/**
 * No-op implementation of [AgentCheckpointStorageProvider].
 */
public class NoAgentCheckpointStorageProvider: AgentCheckpointStorageProvider {
    private val logger = KotlinLogging.logger {  }

    override suspend fun getCheckpoint(
        checkpointId: String
    ): AgentCheckpointData? {
        logger.info { "Snapshot feature is not enabled in the agent. No snapshot will be loaded for agentId: '$checkpointId', snapshotId: '$checkpointId'" }
        return null
    }

    override suspend fun saveCheckpoint(
        checkpointId: String,
        agentCheckpointData: AgentCheckpointData
    ) {
        logger.info { "Snapshot feature is not enabled in the agent. Snapshot will not be saved: $agentCheckpointData" }
    }
}