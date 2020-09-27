package com.example.tictactoe

class Utils(){
    companion object{
        // Traversal: all 8 win conditions as below
        fun ifWin(array: ArrayList<Int>): Boolean{
            if(array.contains(1) && array.contains(2) && array.contains(3))
                return true
            if(array.contains(7) && array.contains(8) && array.contains(9))
                return true
            if(array.contains(1) && array.contains(4) && array.contains(7))
                return true
            if(array.contains(3) && array.contains(6) && array.contains(9))
                return true
            if(array.contains(5)){
                if(array.contains(1) && array.contains(9))
                    return true
                if(array.contains(3) && array.contains(7))
                    return true
                if(array.contains(2) && array.contains(8))
                    return true
                if(array.contains(4) && array.contains(6))
                    return true
            }
            return false
        }

        fun getLineSpots(array: ArrayList<Int>): Int{
            var result: Int = 0
            if(array.contains(1) && array.contains(2) && array.contains(3))
                result = 1

            if(array.contains(7) && array.contains(8) && array.contains(9))
                result = 3

            if(array.contains(1) && array.contains(4) && array.contains(7))
                result = 4

            if(array.contains(3) && array.contains(6) && array.contains(9))
                result = 6

            if(array.contains(5)){
                if(array.contains(1) && array.contains(9))
                    result = 7

                if(array.contains(3) && array.contains(7))
                    result = 8

                if(array.contains(2) && array.contains(8))
                    result = 5

                if(array.contains(4) && array.contains(6))
                    result = 2

            }
            return result
        }
    }

}