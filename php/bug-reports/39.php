<?php

    function x(&$a) {
        /* reference mismatch, copy stored */
        $y = $a;
        $y []= '1'; //local copy modification
        $a []= '2'; //origin modification

        /* legal */
        $z = &$a;   unset($z);

        return $y;
    }

    function process(&$parameter) {
        /* legal */
        array_push($parameter, 'value');
        /* reference mismatch */
        return in_array('value', $parameter, true);
    }

    function & collector() {
        static $collection = array();
        return $collection;
    }

    /* illegal, in spite of reference returned, copy will be obtained */
    $collection = collector();  $collection[] = '1';
    /* legal, reference preserved */
    $collection = &collector(); $collection[] = '2';

    /* "... = &..."          => can introduce mismatches */
    /* " foreach (... &...)" => can introduce mismatches */