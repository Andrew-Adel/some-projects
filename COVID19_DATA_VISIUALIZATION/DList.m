classdef DList < handle % for Global and Countries
    properties
        Head
        Tail
        Length
        Index_Countries
        Name
    end
    methods
        function a = DList(name,ind)
            if nargin ==2
                a.Name = name;
                a.Index_Countries = ind;
            end
            a.Head = [];
            a.Tail = [];
            a.Length = 0;
            
        end
        function insert(Parent ,node)            
            if Parent.Length == 0
                Parent.Head = node;
            else
                Parent.Tail.Next = node;
            end
            node.Next = [];
            node.Prev = Parent.Tail;
            Parent.Tail = node;
            Parent.Length = Parent.Length + 1;
            node.Owner = Parent;
        end
    end
end