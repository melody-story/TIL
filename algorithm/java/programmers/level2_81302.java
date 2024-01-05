/*
* 거리두기 확인하기 Level 2
* https://school.programmers.co.kr/learn/courses/30/lessons/81302
*/

public class Solution {
    final private static int[] dx = {0, -1, 1, 0}; // 상 0, 좌1, 우2, 하3
    final private static int[] dy = {1, 0, 0, -1};

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < answer.length; i++) {
            String[] place = places[i];
            char[][] room = new char[place.length][];
            for (int j = 0; j < place.length; j++) {
                room[j] = place[j].toCharArray();
            }
            answer[i] = isDistanced(room) ? 1 : 0;
            // 거리두기 확인 후 answer에 기록
        }
        return answer;
    }

    public boolean isDistanced(char[][] room) {
        for (int y = 0; y < room.length; y++) {
            for (int x = 0; x < room[y].length; x++) {
                if (room[y][x] != 'P') continue;
                if (!isDistanced(room, x, y)) return false;
            }
        }
        //거리두기 검사
        return true;
    }

    public boolean isDistanced(char[][] room, int x, int y) {
        //room에서 [x,y]가 거리두기를 지키는지 확인
        // 상하 좌우 비교
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            int exclude = 3 - d; //  반대 방향의 인덱스
            if (ny >= room.length || ny < 0 || nx >= room[ny].length || nx < 0) continue;
            switch (room[ny][nx]) {
                case 'P':
                    return false;
                case 'O':
                    if (isNextVolunteer(room, nx, ny, exclude)) return false;
            }
        }
        return true;
    }

    public boolean isNextVolunteer(char[][] room, int x, int y, int exclude) {
        for (int d = 0; d < 4; d++) {
            if (d == exclude) continue;
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (ny >= room.length || ny < 0 || nx >= room[ny].length || nx < 0) continue;
            if (room[ny][nx] == 'P') return true;
        }
        return false;
    }
}
