/**
 * @author yaoyizhou
 * @Doc ����Ⱥ�㷨
 * @date 2019-11-29
 *
 */
public class AlgorithmPSO {
    /**
     * ���Ӹ���������Ϊ�˷�����ʾ������ֻȡ�������۲����˶�����
     */
    int n = 2;
    /**
     * ����
     */
    double[] x;
    /**
     * ����
     */
    double[] y;
    /**
     * ����
     */
    double[] v;
    /**
     * ��֪����,�������ֲ����ŵ����������
     */
    double c1=2;
    /**
     * �����Ϣ,�������ȫ�����ŵ����������
     */
    double c2=2;
    /**
     * ÿ�������Լ���ĿǰΪֹ���ֵ����λ��(pbest)
     */
    double pbest[];
    /**
     * ÿ�����ӻ�֪����ĿǰΪֹ����Ⱥ�����������ӷ��ֵ����λ��(gbest)(gbest��pbest�е����ֵ)
     */
    double gbest;
    /**
     * �ٶ����ֵ
     */
    double vmax = 0.1;

    /**
     * ��Ӧ�ȼ��㺯����ÿ�����Ӷ���������Ӧ��
     */
    public void fitnessFunction(){
        for (int i = 0; i < n; i++) {
            y[i]=-1*x[i]*(x[i]-2);
        }
    }

    public void init(){ //��ʼ��
        x = new double[n];
        v = new double[n];
        y = new double[n];
        pbest = new double[n];
        /***
         * ������Ӧ����������ģ�Ϊ�˷�����ʾ���������ֶ�����������㣬�ֱ��������ֵ����
         */
        x[0] = 0.0;
        x[1] = 2.0;
        v[0] = 0.01;
        v[1] = 0.02;
        /**
         * ��ʼ��Ӧ�ȼ���
         */
        fitnessFunction();
        //��ʼ����ǰ��������λ�ã����ҵ�Ⱥ������λ��
        for (int i = 0; i < n; i++) {
            pbest[i] = y[i];
            if (y[i] > gbest) {
                gbest = y[i];
            }
        }
        System.out.println("�㷨��ʼ����ʼ���Ž�:"+gbest);
        System.out.print("\n");
    }

    public double getMAX(double a, double b) {
        return a > b ? a : b;
    }

    //����Ⱥ�㷨
    public void PSO(int max){
        for (int i = 0; i < max; i++) {
            double w=0.4;
            for (int j = 0; j < n; j++) {
                //����λ�ú��ٶȣ������������֮ǰ�ص㽲���������ʽ��
                v[j]=w*v[j]+c1*Math.random()*(pbest[j]-x[j])+c2*Math.random()*(gbest-x[j]);
                //�����ٶȲ��������ֵ
                if (v[j] > vmax) {
                    v[j] = vmax;
                }
                x[j]+=v[j];

                //Խ���жϣ���Χ�޶���[0, 2]
                if (x[j] > 2) {
                    x[j] = 2;
                }
                if (x[j] < 0) {
                    x[j] = 0;
                }
            }
            fitnessFunction();
            //���¸��弫ֵ��Ⱥ�弫ֵ
            for (int j = 0; j < n; j++) {
                pbest[j] = getMAX(y[j], pbest[j]);
                if (pbest[j] > gbest) {
                    gbest = pbest[j];
                }
                System.out.println("����n"+j+": x = "+x[j]+"  "+"v = "+v[j]);
            }
            System.out.println("��"+(i+1)+"�ε�����ȫ�����Ž� gbest = "+gbest);
            System.out.print("\n");
        }

    }

    /**
     * �������ǵ��㷨
     * @param args
     */
    public static void main(String[] args){
        AlgorithmPSO ts = new AlgorithmPSO();
        ts.init();
        //Ϊ�˷�����ʾ��������ʱ����10�Ρ�
        ts.PSO(100);

//        datatest.a = 10;
//
//        System.out.println("a = "+datatest.a);
//
//        datatest.a = 2;
//
//        System.out.println("a = "+datatest.a);

    }

}
