package ai.koog.agents.core.agent.entity

/**
 * Means that the entity has subnodes.
 * */
public interface HasSubnodes {

    /**
     * Forces the entity to use a specific node for execution.
     */
    public var forcedNode: AIAgentNodeBase<*, *>?

    /**
     * Holds the input data to be passed explicitly to the forced node during its execution.
     */
    public var forcedInput: Any?

    /**
     * Sets a forced node for the entity.
     */
    public fun enforceNode(node: AIAgentNodeBase<*, *>, input: Any? = null) {
        if (forcedNode != null) {
            throw IllegalStateException("Forced node is already set to ${forcedNode!!.name}")
        }
        forcedNode = node
        forcedInput = input
    }
}