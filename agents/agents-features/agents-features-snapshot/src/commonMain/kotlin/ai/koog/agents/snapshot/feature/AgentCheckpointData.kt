@file:Suppress("MissingKDocForPublicAPI") // TODO REMOVE
@file:OptIn(InternalAgentsApi::class)

package ai.koog.agents.snapshot.feature

import ai.koog.agents.core.agent.context.AgentContextData
import ai.koog.agents.core.annotation.InternalAgentsApi
import ai.koog.prompt.message.Message

public class AgentCheckpointData(
    public val checkpointId: String,
    public val messageHistory: List<Message>,
    public val nodeId: String,
    public val lastInput: Any?,
)

public fun AgentCheckpointData.toAgentContextData(): AgentContextData {
    return AgentContextData(
        messageHistory = messageHistory,
        nodeId = nodeId,
        lastInput = lastInput
    )
}