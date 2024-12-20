def dynamic_array(n, queries):
    """
    Simulates a dynamic array with sequence list operations
    
    Args:
        n: int - Number of sequences to initialize
        queries: List[List[int]] - List of queries where each query is [type, x, y]
            type 1: append y to sequence ((x ^ lastAnswer) % n)
            type 2: assign lastAnswer = sequence[(x ^ lastAnswer) % n][y % size]
    
    Returns:
        List[int] - List of answers from type 2 queries
    """
    sequences = [[] for _ in range(n)]
    last_answer = 0
    answers = []
    
    for query in queries:
        query_type, x, y = query
        seq_idx = (x ^ last_answer) % n
        
        if query_type == 1:
            # Append y to sequence at seq_idx
            sequences[seq_idx].append(y)
        else:  # query_type == 2
            # Get value at index y % size in sequence at seq_idx
            size = len(sequences[seq_idx])
            last_answer = sequences[seq_idx][y % size]
            answers.append(last_answer)
    
    return answers

# Example usage:
if __name__ == "__main__":
    n = 2
    queries = [
        [1, 0, 5],
        [1, 1, 7],
        [1, 0, 3],
        [2, 1, 0],
        [2, 1, 1]
    ]
    result = dynamic_array(n, queries)
    print(f"Results: {result}")