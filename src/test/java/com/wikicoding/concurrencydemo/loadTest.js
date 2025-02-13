import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
    stages: [
        { duration: '30s', target: 1000 }, // ramp up
        { duration: '90s', target: 1000 }, // stable
        { duration: '30s', target: 0 }, // ramp down to users
    ]
};

export default () => {
    const res = http.get('http://192.168.1.91:8080/movies/sync');
    check(res, { '200': (r) => r.status === 200 });
    sleep(1);
};

// runs with: cat loadTest.js | docker run --rm -i grafana/k6 run -