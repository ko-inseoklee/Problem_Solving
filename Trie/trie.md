## trie 자료구조

### 1. 특징
- 자료구조 안에 데이터를 한 글자 단위로 저장.
- EOW(end of word): 문자가 끝나는 위치
    - ex) app, appl.
        - a -> p -> p(EOW) -> l(EOW)
        - 두 단어의 끝을 표시해줘야 함.
- 탐색 시, 접두사가 어디까지 오는지 확인하기 위한 용도로 사용.
    - prefix tree라고도 함.


### 2. 구현

``` java
class Trie {
    char c;
    boolean isEndOfWord;
    Map<Character, Trie> children;

    public Trie(char c) {
        this.c = c;
        this.isEndOfWord = false;
        this.children = new HashMap<>();

    }

    public Trie insert (String value) {
        Trie current = this;

        for (char ch : value.toCharArray()) {
            Trie child = current.children.get(ch);

            if(child == null) {
                child = new Trie(ch);
            } else {
                // child.count += 1; // This was for the old 'count' field, no longer needed.
            }
            
            current.children.put(ch, child);
            current = child;
        }
        current.isEndOfWord = true;
        return this;
    }

    public boolean search (String value) {
        Trie current = this;

        for (char ch : value.toCharArray()) {
            Trie child = current.children.get(ch);
            if (child == null) {
                return false;
            }
            current = child;
        }
        return current.isEndOfWord; 
    }

    public boolean startsWith (String prefix) {
        Trie current = this;

        for (char ch : prefix.toCharArray()) {
            Trie child = current.children.get(ch);
            if (child == null) {
                return false;
            }
            current = child;
        }
        return true;
    }

    public void delete (String value) {
        Trie current = this;
        for (char ch: value.toCharArray()) {
            Trie child = current.children.get(ch);
            if (child == null) {
                return;
            }

            current = child;
        }

        current.isEndOfWord = false;
    }
}

```

### 3. 생각

- 값이 들어올 때마다 depth가 char 갯수만큼 늘어야 해서 메모리 낭비가 심할 수 있음 -> 해시 맵으로 대체했지만, 검색어가 많은 경우 성능 저하가 우려됨.

- 삭제 시 soft delete를 차용하는 것이 연산 측면에 유리하다고 생각이 듬. 데이터 수정이 빈번하게 발생할 때, 인스턴스를 새로 생성하고 삭제하는 코스트보다 적을 것으로 생각됨. 이럴 경우, 메모리 낭비라는 Trade-Off가 생길 수 있음.