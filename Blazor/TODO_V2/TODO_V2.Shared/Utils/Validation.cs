﻿using BlazorBootstrap;
using System;
using System.Linq.Expressions;
using System.Text.RegularExpressions;
using TODO_V2.Shared.Models.Enum;

namespace TODO_V2.Shared.Utils
{
    public static class Validation
    {       
        private static Regex Numeric { get; set; } = new Regex(@"^[0-9\s,]*$");


        public static bool CheckFormat(string word, string FieldType)
        {
            if (!string.IsNullOrWhiteSpace(word) && word.Length >= 3 && word.Length <= 25)
            {
                return FieldType switch
                {
                    "Alphabetical" => word.All(Char.IsLetter),
                    "AlphaNumeric" => word.All(Char.IsLetterOrDigit),
                    "All" => Numeric.IsMatch(word),
                    _ => false,
                };
            }
            return false;
        }


        public static bool CheckKey(string key)
        {
            return Enum.IsDefined(typeof(Keys), key);
        }
    }
}
