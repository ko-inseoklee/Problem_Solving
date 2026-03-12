import java.util.*;

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        
        Node root = new Node(' ');
        
        for(String word: words) {
            insert(root, word);
        }
        
        for(String word: words) {
            answer += find_word(root, word);
        }
        
        
        // printTrie(root, "   ");
        
        return answer;
    }
    
    public void insert(Node root, String word){
        Node curr = root;
        
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            
            Map<Character, Node> children = curr.children;
            
            Node n = children.get(c);
            if(n == null) {
                n = new Node(c);
            } else {
                n.count++;
            }
            
            children.put(c, n);
            curr = n;
        }
    };
    
    public int find_word(Node root, String word) {
        Node curr = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Map<Character, Node> children = curr.children;
            curr = children.get(c);
            
            if(curr.count == 1) {
                return i + 1;
            }
        }
        
        return word.length();
        
    }
    
    public void printTrie(Node node, String indent) {
    // 현재 노드 정보 출력 (루트 노드는 보통 토큰이 없으므로 예외 처리)
    if (node.token != '\0') {
        System.out.println(indent + "├── [" + node.token + "] (count: " + node.count + ")");
    } else {
        System.out.println("Root");
    }

    // 자식 노드들을 재귀적으로 출력
    for (char key : node.children.keySet()) {
        printTrie(node.children.get(key), indent + "    ");
    }
}
    
}

class Node {
    int count = 0;
    char token;
    Map<Character, Node> children;
    
    public Node (char t) {
        this.token = t;
        this.count = 1;
        this.children = new HashMap<>();
    }
}