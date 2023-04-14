class Solution {
    int answer = Integer.MAX_VALUE;
    public int solution(int[] picks, String[] minerals) {
        if(picks[0]!=0){
            int[] pick=picks.clone();
            pick[0]--;
            dfs(0,0,"diamond",pick,minerals);
        }
        if(picks[1]!=0){
            int[] pick=picks.clone();
            pick[1]--;
            dfs(0,0,"iron",pick,minerals);
        }
        if(picks[2]!=0){
            int[] pick=picks.clone();
            pick[2]--;
            dfs(0,0,"stone",pick,minerals);
        }
        return answer;
    }
    public void dfs(int sum, int index,String mineral,int[] picks,String[] minerals){
        for(int i=index;i<index+5;i++){
            if(i==minerals.length){
                answer=Math.min(answer,sum);
                return;
            }
            if(mineral.equals("diamond")) sum+=1;
            else if(mineral.equals("iron")){
                if(minerals[i].equals("diamond")) sum+=5;
                else sum+=1;
            }
            else{
                if(minerals[i].equals("diamond")) sum+=25;
                else if(minerals[i].equals("iron")) sum+=5;
                else sum+=1;
            }
        }
        boolean hasNext=false;
        if(picks[0]>0){
            int[] pick=picks.clone();
            pick[0]--;
            hasNext=true;
            dfs(sum,index+5,"diamond",pick,minerals);
        }
        if(picks[1]>0){
            int[] pick=picks.clone();
            pick[1]--;
            hasNext=true;
            dfs(sum,index+5,"iron",pick,minerals);
        }
        if(picks[2]>0){
            int[] pick=picks.clone();
            pick[2]--;
            hasNext=true;
            dfs(sum,index+5,"stone",pick,minerals);
        }
        if(!hasNext) answer=Math.min(answer,sum);
    }
}