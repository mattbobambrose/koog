package ai.koog.agents.example.snapshot

import ai.koog.agents.core.dsl.builder.AIAgentNodeDelegateBase
import ai.koog.agents.core.dsl.builder.AIAgentSubgraphBuilderBase
import ai.koog.agents.core.dsl.builder.forwardTo
import ai.koog.agents.core.dsl.builder.strategy
import ai.koog.agents.snapshot.feature.withCheckpoints

private fun AIAgentSubgraphBuilderBase<*, *>.simpleNode(
    name: String? = null,
    output: String,
): AIAgentNodeDelegateBase<String, String> = node(name) {
    return@node it + output
}

private fun AIAgentSubgraphBuilderBase<*, *>.teleportNode(
    name: String? = null,
): AIAgentNodeDelegateBase<String, String> = node(name) {
    val ctx = this
    withCheckpoints {
        setExecutionPoint(ctx, "Node1", listOf(), "Teleported!!!")
        return@withCheckpoints "Teleported"
    }
}

object SnapshotStrategy2 {
    val strategy = strategy("test") {
        val node1 by simpleNode(
            "Node1",
            output = "Node 1 output"
        )
        val node2 by simpleNode(
            "Node2",
            output = "Node 2 output"
        )
        val teleportNode by teleportNode()

        edge(nodeStart forwardTo node1)
        edge(node1 forwardTo node2)
        edge(node2 forwardTo teleportNode)
        edge(teleportNode forwardTo nodeFinish)
    }
}