package goorm_study;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502 {
    static int n,m,res;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        createWall(0);

        bw.write(res +"\n");
        bw.flush();
        bw.close();
    }
    public static void createWall(int stage){
        if(stage==3){
            spreadVirus();
            return;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j]==0){
                    map[i][j]=1;
                    createWall(stage+1);
                    map[i][j]=0;

                }
            }
        }
    }

        public static void spreadVirus() {
            Queue<int[]> q = new LinkedList<>();

            int safeZone = 0;
            int[][] newMap = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    newMap[i][j] = map[i][j];
                    if (newMap[i][j] == 2) { // 바이러스 큐에 다 집어넣기
                        q.offer(new int[]{i, j});
                    }

                    if (newMap[i][j] == 0) { // 전체 안전개수 세기
                        safeZone++;
                    }
                }
            }

            while(!q.isEmpty()){
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];

                for(int i=0;i<4;i++){
                    int nx = x+dx[i];
                    int ny = y+dy[i];
                    if(nx < 0 || nx >= n || ny < 0 || ny >= m){
                        continue;
                    }

                    if(newMap[nx][ny] == 0){
                        newMap[nx][ny] = 2;
                        q.offer(new int[]{nx,ny});
                        safeZone--; // 안전영역 개수 감소
                    }
                }
            }

            res = Math.max(res,safeZone);
    }

}
