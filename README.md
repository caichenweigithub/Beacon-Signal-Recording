# Beacon-Signal-Recording
Receive beacon rssi and make records.

功能：可以同時接收n個beacon，並記錄一段時間內的rssi值，將n個beacon的rssi分別存成txt檔。
步驟：
1. 下載此repository
2. 用Android Studio打開
3. 更改minor值。只填想接收到的beacon minor值。
4. 燒錄程式到手機
5. 打開APP就會開始記錄rssi值，一秒記錄一次，直到按下停止按紐。如果要重新測試，需完全重開APP。
6. 在內建記憶體的Download資料夾可以看見所存的txt檔

txt檔格式：
* 檔名：第幾個檔案_年月日 小時_分鐘_秒(number_yyyyMMdd kk_mm_ss)
* 內容：「空格+新的rssi」會append在舊的字串後面，ex. 舊的字串 = " -90"，append新的變成 = " -90 -85"
