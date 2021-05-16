package indi.mzt.toppahs.entity;

import indi.mzt.toppahs.service.ScramblesService;
import net.mamoe.mirai.utils.ExternalResource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.worldcubeassociation.tnoodle.scrambles.Puzzle;

import java.io.File;


public class Scramble {
    private Puzzle puzzle;
    private String scramble;
    private File puzzleImage;
    public File getPuzzleImage() {
        return puzzleImage;
    }
    public String getScramble() {
        return scramble;
    }
    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.scramble =this.puzzle.generateScramble();
    }
    public void setPuzzleImage(File puzzleImage) {
        this.puzzleImage = puzzleImage;
    }
}
