# Sync get run execution results (2min test)
```bash
     checks.........................: 99.84% 7941 out of 7953
     data_received..................: 6.6 GB 55 MB/s
     data_sent......................: 747 kB 6.2 kB/s
     http_req_blocked...............: avg=28.7ms  min=0s      med=4.5µs   max=19.01s   p(90)=9.4µs   p(95)=16.64µs
     http_req_connecting............: avg=28.7ms  min=0s      med=0s      max=19.01s   p(90)=0s      p(95)=0s     
     http_req_duration..............: avg=65.46ms min=0s      med=66.83ms max=310.82ms p(90)=92.1ms  p(95)=99.89ms
       { expected_response:true }...: avg=65.56ms min=24.18ms med=66.91ms max=310.82ms p(90)=92.12ms p(95)=99.92ms
     http_req_failed................: 0.15%  12 out of 7953
     http_req_receiving.............: avg=8.37ms  min=0s      med=6.88ms  max=215ms    p(90)=12.26ms p(95)=14.22ms
     http_req_sending...............: avg=15.42µs min=0s      med=11.45µs max=1.52ms   p(90)=26.79µs p(95)=37.04µs
     http_req_tls_handshaking.......: avg=0s      min=0s      med=0s      max=0s       p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=57.07ms min=0s      med=58.61ms max=257.92ms p(90)=83.31ms p(95)=90.21ms
     http_reqs......................: 7953   66.036239/s
     iteration_duration.............: avg=1.14s   min=1.02s   med=1.06s   max=31.01s   p(90)=1.09s   p(95)=1.1s   
     iterations.....................: 7953   66.036239/s
     vus............................: 3      min=3            max=100
     vus_max........................: 100    min=100          max=100
```

## 1000 VUs during 2min and 30 sec total (db received this requests before)
```bash
checks.........................: 100.00% 28037 out of 28037
     data_received..................: 23 GB   154 MB/s
     data_sent......................: 2.6 MB  18 kB/s
     http_req_blocked...............: avg=73.46µs min=958ns    med=4.5µs  max=11.52ms  p(90)=8.04µs  p(95)=20.05µs
     http_req_connecting............: avg=66.45µs min=0s       med=0s     max=11.49ms  p(90)=0s      p(95)=0s     
     http_req_duration..............: avg=3.33s   min=24.34ms  med=4.03s  max=6.58s    p(90)=4.35s   p(95)=4.46s  
       { expected_response:true }...: avg=3.33s   min=24.34ms  med=4.03s  max=6.58s    p(90)=4.35s   p(95)=4.46s  
     http_req_failed................: 0.00%   0 out of 28037
     http_req_receiving.............: avg=8.8ms   min=254.54µs med=7.44ms max=226.31ms p(90)=11.17ms p(95)=12.51ms
     http_req_sending...............: avg=14.71µs min=1.95µs   med=11.5µs max=933.62µs p(90)=21.33µs p(95)=33.16µs
     http_req_tls_handshaking.......: avg=0s      min=0s       med=0s     max=0s       p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=3.32s   min=18.67ms  med=4.03s  max=6.57s    p(90)=4.34s   p(95)=4.45s  
     http_reqs......................: 28037   185.902195/s
     iteration_duration.............: avg=4.33s   min=1.02s    med=5.03s  max=7.58s    p(90)=5.35s   p(95)=5.46s  
     iterations.....................: 28037   185.902195/s
     vus............................: 20      min=20             max=1000
     vus_max........................: 1000    min=1000           max=1000
```

# Async get with parallel processing results (2min test, db received this requests before)
```bash
checks.........................: 100.00% 8547 out of 8547
     data_received..................: 7.1 GB  59 MB/s
     data_sent......................: 812 kB  6.7 kB/s
     http_req_blocked...............: avg=30.01µs min=750ns       med=4µs     max=47.17ms  p(90)=8.83µs  p(95)=15.12µs 
     http_req_connecting............: avg=23.84µs min=0s          med=0s      max=47.12ms  p(90)=0s      p(95)=0s      
     http_req_duration..............: avg=59.95ms min=23.43ms     med=58.27ms max=306.99ms p(90)=92.22ms p(95)=100.73ms
       { expected_response:true }...: avg=59.95ms min=23.43ms     med=58.27ms max=306.99ms p(90)=92.22ms p(95)=100.73ms
     http_req_failed................: 0.00%   0 out of 8547
     http_req_receiving.............: avg=6.67ms  min=-12932305ns med=5.51ms  max=215.33ms p(90)=9.02ms  p(95)=10.69ms 
     http_req_sending...............: avg=15.12µs min=1.95µs      med=10.75µs max=1.56ms   p(90)=25.12µs p(95)=35.95µs 
     http_req_tls_handshaking.......: avg=0s      min=0s          med=0s      max=0s       p(90)=0s      p(95)=0s      
     http_req_waiting...............: avg=53.26ms min=17.91ms     med=51.69ms max=157.25ms p(90)=85.35ms p(95)=93.19ms 
     http_reqs......................: 8547    71.046223/s
     iteration_duration.............: avg=1.06s   min=1.02s       med=1.05s   max=1.3s     p(90)=1.09s   p(95)=1.1s    
     iterations.....................: 8547    71.046223/s
     vus............................: 3       min=3            max=100
     vus_max........................: 100     min=100          max=100
```

## 1000 VUs during 2min and 30 sec total (db received this requests before)
```bash
checks.........................: 100.00% 28523 out of 28523
     data_received..................: 24 GB   156 MB/s
     data_sent......................: 2.7 MB  18 kB/s
     http_req_blocked...............: avg=74.81µs min=875ns    med=4.41µs  max=15.08ms  p(90)=8.12µs p(95)=19.08µs
     http_req_connecting............: avg=67.56µs min=0s       med=0s      max=15.03ms  p(90)=0s     p(95)=0s     
     http_req_duration..............: avg=3.25s   min=23.76ms  med=3.93s   max=9.23s    p(90)=4.16s  p(95)=4.22s  
       { expected_response:true }...: avg=3.25s   min=23.76ms  med=3.93s   max=9.23s    p(90)=4.16s  p(95)=4.22s  
     http_req_failed................: 0.00%   0 out of 28523
     http_req_receiving.............: avg=7.51ms  min=275.37µs med=6.11ms  max=253.67ms p(90)=9.13ms p(95)=10.3ms 
     http_req_sending...............: avg=14.97µs min=1.87µs   med=11.33µs max=1.31ms   p(90)=21.2µs p(95)=33.25µs
     http_req_tls_handshaking.......: avg=0s      min=0s       med=0s      max=0s       p(90)=0s     p(95)=0s     
     http_req_waiting...............: avg=3.25s   min=18.14ms  med=3.92s   max=9.22s    p(90)=4.15s  p(95)=4.21s  
     http_reqs......................: 28523   188.940156/s
     iteration_duration.............: avg=4.25s   min=1.02s    med=4.93s   max=10.23s   p(90)=5.16s  p(95)=5.22s  
     iterations.....................: 28523   188.940156/s
     vus............................: 0       min=0              max=1000
     vus_max........................: 1000    min=1000           max=1000
```