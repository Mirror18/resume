package com.mirror.堆内存申请;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //获取输入流
        Scanner scanner = new Scanner(System.in);
        //获取到要分配的空间大小
        int malloc_size  = Integer.parseInt(scanner.nextLine());
        //创建一个动态数组用于存储已分配的空间
        ArrayList<Memory> used_memory = new ArrayList<>();
        //当还有输入行的时候
        while(scanner.hasNextLine()){
            //获取到输入行
            String line = scanner.nextLine();
            //本地测试结束条件
            if(line.length() == 0) break;;
            //对输入行进行处理变为数字
            int[] tmp = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            //将已分配的空间加入到动态数组中
            used_memory.add(new Memory(tmp[0],tmp[1]));
        }
        //处理动态数组并打印结果
        System.out.println(getResult(malloc_size,used_memory));
    }

    private static int getResult(int malloc_size, ArrayList<Memory> used_memory) {
        //当想要分配的空间不在分配范围内
        if(malloc_size <= 0 || malloc_size> 100){
            return  -1;
        }
        //最优的申请内存的起始位置
        int malloc_offset = -1;
        //记录最接近申请内存的空闲内存块大小
        int min_malloc_size = 100;
        //对已经申请的内存进行排序
        used_memory.sort((a,b)-> a.offset - b.offset);
        //记录空闲内存块的起始位置
        int free_offset = 0;
        for(Memory used : used_memory){
            //如果占用的内存起始位置＜前面一个空闲内存块的起始位置，则存在占用内存区域重叠
            if(used.offset <free_offset || used.offset> 99){
                return  -1;
            }
            //输入的占用内存的大小存在非法情况
            if(used.size <= 0 || used.size > 100 - used.offset){
                return -1;
            }
            //计算当前空闲内存块的大小
            if(used.offset > free_offset){
                //空闲内存块大小
                int free_memory_size = used.offset - free_offset;
                //并与malloc_size进行比较，找到最合适的分配起始位置
                if(free_memory_size >= malloc_size && free_memory_size <min_malloc_size){
                    malloc_offset = free_offset;
                    min_malloc_size = free_memory_size;
                }
            }
            //更新，空闲内存的起始位置
            free_offset = used.offset + used.size;
        }
        //检查最后一个空闲内存块的大小
        int last_free_memory_size = 100 - free_offset;
        if(last_free_memory_size >= malloc_size && last_free_memory_size < min_malloc_size){
            malloc_offset = free_offset;
        }
        return  malloc_offset;
    }

    static class Memory{
        //内存块的起始位置
        int offset;
        //内存块大小
        int  size;
        public Memory(int offset , int size){
            this.offset = offset;
            this.size = size;
        }
    }
}
