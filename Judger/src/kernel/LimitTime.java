package kernel;

import resultData.JudgerInfo;



    class LimitTime implements Runnable { // ���ڿ���ʱ��Ķ��߳�

        private long timeLimit;
        private Process p;

        public void setProcess(Process p) {
            this.p = p;
        }

        public void setTimeLimit(long timeLimit) {
            this.timeLimit = timeLimit*1000;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            long startTime = System.currentTimeMillis();
            while (true) {
                long endTime = System.currentTimeMillis();
                if (JudgerInfo.isRun == 0) {
                    return;
                }
                if ((endTime - startTime) > timeLimit) {
                    p.destroy();
                    JudgerInfo.isKilled = 1;
                    return;
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    // e.printStackTrace();
                }
            }
        }

    }
