/**
 * @author yaoyizhou
 * @Doc 粒子群算法
 * @date 2019-11-29
 *
 */
public class AlgorithmPSO {
    /**
     * 粒子个数，这里为了方便演示，我们只取两个，观察其运动方向
     */
    int n = 2;
    /**
     * 变量
     */
    double[] x;
    /**
     * 变量
     */
    double[] y;
    /**
     * 方向
     */
    double[] v;
    /**
     * 认知能力,可理解与局部最优迭代方向相关
     */
    double c1=2;
    /**
     * 社会信息,可理解与全局最优迭代方向相关
     */
    double c2=2;
    /**
     * 每个粒子自己到目前为止发现的最好位置(pbest)
     */
    double pbest[];
    /**
     * 每个粒子还知道到目前为止整个群体中所有粒子发现的最好位置(gbest)(gbest是pbest中的最好值)
     */
    double gbest;
    /**
     * 速度最大值
     */
    double vmax = 0.1;

    /**
     * 适应度计算函数，每个粒子都有它的适应度
     */
    public void fitnessFunction(){
        for (int i = 0; i < n; i++) {
            y[i]=-1*x[i]*(x[i]-2);
        }
    }

    public void init(){ //初始化
        x = new double[n];
        v = new double[n];
        y = new double[n];
        pbest = new double[n];
        /***
         * 本来是应该随机产生的，为了方便演示，我这里手动随机落两个点，分别落在最大值两边
         */
        x[0] = 0.0;
        x[1] = 2.0;
        v[0] = 0.01;
        v[1] = 0.02;
        /**
         * 初始适应度计算
         */
        fitnessFunction();
        //初始化当前个体最优位置，并找到群体最优位置
        for (int i = 0; i < n; i++) {
            pbest[i] = y[i];
            if (y[i] > gbest) {
                gbest = y[i];
            }
        }
        System.out.println("算法开始，起始最优解:"+gbest);
        System.out.print("\n");
    }

    public double getMAX(double a, double b) {
        return a > b ? a : b;
    }

    //粒子群算法
    public void PSO(int max){
        for (int i = 0; i < max; i++) {
            double w=0.4;
            for (int j = 0; j < n; j++) {
                //更新位置和速度，下面就是我们之前重点讲解的两条公式。
                v[j]=w*v[j]+c1*Math.random()*(pbest[j]-x[j])+c2*Math.random()*(gbest-x[j]);
                //控制速度不超过最大值
                if (v[j] > vmax) {
                    v[j] = vmax;
                }
                x[j]+=v[j];

                //越界判断，范围限定在[0, 2]
                if (x[j] > 2) {
                    x[j] = 2;
                }
                if (x[j] < 0) {
                    x[j] = 0;
                }
            }
            fitnessFunction();
            //更新个体极值和群体极值
            for (int j = 0; j < n; j++) {
                pbest[j] = getMAX(y[j], pbest[j]);
                if (pbest[j] > gbest) {
                    gbest = pbest[j];
                }
                System.out.println("粒子n"+j+": x = "+x[j]+"  "+"v = "+v[j]);
            }
            System.out.println("第"+(i+1)+"次迭代，全局最优解 gbest = "+gbest);
            System.out.print("\n");
        }

    }

    /**
     * 运行我们的算法
     * @param args
     */
    public static void main(String[] args){
        AlgorithmPSO ts = new AlgorithmPSO();
        ts.init();
        //为了方便演示，我们暂时迭代10次。
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
